define(['controller/selectionController', 'model/cacheModel', 'model/wikiMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/wikiComponent',
 'component/estudianteComponent', 'component/problemaComponent'],
 function(SelectionController, CacheModel, WikiMasterModel, CRUDComponent, TabController, WikiComponent,
 dueniosComponent, problemaComponent) {
    App.Component._WikiMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('wikiMaster');
            App.Model.WikiMasterModel.prototype.urlRoot = this.configuration.context;
            this.componentId = App.Utils.randomInteger();
            
            this.masterComponent = new WikiComponent();
            this.masterComponent.initialize();
            
            this.childComponents = [];
			
			this.initializeChildComponents();
            
            Backbone.on(this.masterComponent.componentId + '-post-wiki-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-post-wiki-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-pre-wiki-list', function() {
                self.hideChilds();
            });
            Backbone.on('wiki-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'wiki-master-save', view: self, message: error});
            });
            Backbone.on(this.masterComponent.componentId + '-instead-wiki-save', function(params) {
                self.model.set('wikiEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }

				App.Utils.fillCacheList(
					'duenios',
					self.model,
					self.dueniosComponent.getDeletedRecords(),
					self.dueniosComponent.getUpdatedRecords(),
					self.dueniosComponent.getCreatedRecords()
				);

				App.Utils.fillCacheList(
					'problema',
					self.model,
					self.problemaComponent.getDeletedRecords(),
					self.problemaComponent.getUpdatedRecords(),
					self.problemaComponent.getCreatedRecords()
				);

                self.model.save({}, {
                    success: function() {
                        Backbone.trigger(self.masterComponent.componentId + '-' + 'post-wiki-save', {view: self, model : self.model});
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'wiki-master-save', view: self, error: error});
                    }
                });
			    if (this.postInit) {
					this.postInit();
				}
            });
        },
        render: function(domElementId){
			if (domElementId) {
				var rootElementId = $("#"+domElementId);
				this.masterElement = this.componentId + "-master";
				this.tabsElement = this.componentId + "-tabs";

				rootElementId.append("<div id='" + this.masterElement + "'></div>");
				rootElementId.append("<div id='" + this.tabsElement + "'></div>");
			}
			this.masterComponent.render(this.masterElement);
		},
		initializeChildComponents: function () {
			this.tabModel = new App.Model.TabModel({tabs: [
                {label: "Duenios", name: "duenios", enable: true},
                {label: "Problema", name: "problema", enable: true}
			]});
			this.tabs = new TabController({model: this.tabModel});

			this.dueniosComponent = new dueniosComponent();
            this.dueniosComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.dueniosComponent);

			this.problemaComponent = new problemaComponent();
            this.problemaComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.problemaComponent);

            var self = this;
            
            this.configToolbar(this.dueniosComponent,false);
            this.dueniosComponent.disableEdit();

            Backbone.on(this.dueniosComponent.componentId + '-toolbar-add', function() {
                var selection = new SelectionController({"componentId":"dueniosComponent"});
                App.Utils.getComponentList('estudianteComponent', function(componentName, model) {
                    if (model.models.length == 0) {
                        alert('There is no Duenioss to select.');
                    } else {
                        selection.showSelectionList({list: model, name: 'name', title: 'Duenios List'});
                    }
                    ;
                });
            });

            Backbone.on('dueniosComponent-post-selection', function(models) {
            	self.dueniosComponent.addRecords(models);
            	self.dueniosComponent.render();
            });

            this.configToolbar(this.problemaComponent,false);
            this.problemaComponent.disableEdit();

            Backbone.on(this.problemaComponent.componentId + '-toolbar-add', function() {
                var selection = new SelectionController({"componentId":"problemaComponent"});
                App.Utils.getComponentList('problemaComponent', function(componentName, model) {
                    if (model.models.length == 0) {
                        alert('There is no Problemas to select.');
                    } else {
                        selection.showSelectionList({list: model, name: 'name', title: 'Problema List'});
                    }
                    ;
                });
            });

            Backbone.on('problemaComponent-post-selection', function(models) {
            	self.problemaComponent.addRecords(models);
            	self.problemaComponent.render();
            });

		},
        renderChilds: function(params) {
            var self = this;
            
            var options = {
                success: function() {
                	self.tabs.render(self.tabsElement);

					self.dueniosComponent.clearCache();
					self.dueniosComponent.setRecords(self.model.get('listduenios'));
					self.dueniosComponent.render(self.tabs.getTabHtmlId('duenios'));

					self.problemaComponent.clearCache();
					self.problemaComponent.setRecords(self.model.get('listproblema'));
					self.problemaComponent.render(self.tabs.getTabHtmlId('problema'));

                    $('#'+self.tabsElement).show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'wiki-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.WikiMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.WikiMasterModel();
                options.success();
            }


        },
        showMaster: function (flag) {
			if (typeof (flag) === "boolean") {
				if (flag) {
					$("#"+this.masterElement).show();
				} else {
					$("#"+this.masterElement).hide();
				}
			}
		},
        hideChilds: function() {
            $("#"+this.tabsElement).hide();
        },
		configToolbar: function(component, composite) {
		    component.removeGlobalAction('refresh');
			component.removeGlobalAction('print');
			component.removeGlobalAction('search');
			if (!composite) {
				component.removeGlobalAction('create');
				component.removeGlobalAction('save');
				component.removeGlobalAction('cancel');
				component.addGlobalAction({
					name: 'add',
					icon: 'glyphicon-send',
					displayName: 'Add',
					show: true
				}, function () {
					Backbone.trigger(component.componentId + '-toolbar-add');
				});
			}
        },
        getChilds: function(name){
			for (var idx in this.childComponents) {
				if (this.childComponents[idx].name === name) {
					return this.childComponents[idx].getRecords();
				}
			}
		},
		setChilds: function(childName,childData){
			for (var idx in this.childComponents) {
				if (this.childComponents[idx].name === childName) {
					this.childComponents[idx].setRecords(childData);
				}
			}
		},
		renderMaster: function(domElementId){
			this.masterComponent.render(domElementId);
		},
		renderChild: function(childName, domElementId){
			for (var idx in this.childComponents) {
				if (this.childComponents[idx].name === childName) {
					this.childComponents[idx].render(domElementId);
				}
			}
		}
    });

    return App.Component._WikiMasterComponent;
});