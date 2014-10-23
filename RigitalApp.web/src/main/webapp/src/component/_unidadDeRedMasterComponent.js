define(['controller/selectionController', 'model/cacheModel', 'model/unidadDeRedMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/unidadDeRedComponent',
 'component/estudianteComponent', 'component/problemaComponent'],
 function(SelectionController, CacheModel, UnidadDeRedMasterModel, CRUDComponent, TabController, UnidadDeRedComponent,
 usuariosComponent, problemasComponent) {
    App.Component._UnidadDeRedMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('unidadDeRedMaster');
            App.Model.UnidadDeRedMasterModel.prototype.urlRoot = this.configuration.context;
            this.componentId = App.Utils.randomInteger();
            
            this.masterComponent = new UnidadDeRedComponent();
            this.masterComponent.initialize();
            
            this.childComponents = [];
			
			this.initializeChildComponents();
            
            Backbone.on(this.masterComponent.componentId + '-post-unidadDeRed-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-post-unidadDeRed-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-pre-unidadDeRed-list', function() {
                self.hideChilds();
            });
            Backbone.on('unidadDeRed-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'unidadDeRed-master-save', view: self, message: error});
            });
            Backbone.on(this.masterComponent.componentId + '-instead-unidadDeRed-save', function(params) {
                self.model.set('unidadDeRedEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }

				App.Utils.fillCacheList(
					'usuarios',
					self.model,
					self.usuariosComponent.getDeletedRecords(),
					self.usuariosComponent.getUpdatedRecords(),
					self.usuariosComponent.getCreatedRecords()
				);

				App.Utils.fillCacheList(
					'problemas',
					self.model,
					self.problemasComponent.getDeletedRecords(),
					self.problemasComponent.getUpdatedRecords(),
					self.problemasComponent.getCreatedRecords()
				);

                self.model.save({}, {
                    success: function() {
                        Backbone.trigger(self.masterComponent.componentId + '-' + 'post-unidadDeRed-save', {view: self, model : self.model});
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'unidadDeRed-master-save', view: self, error: error});
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
                {label: "Usuarios", name: "usuarios", enable: true},
                {label: "Problemas", name: "problemas", enable: true}
			]});
			this.tabs = new TabController({model: this.tabModel});

			this.usuariosComponent = new usuariosComponent();
            this.usuariosComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.usuariosComponent);

			this.problemasComponent = new problemasComponent();
            this.problemasComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.problemasComponent);

            var self = this;
            
            this.configToolbar(this.usuariosComponent,false);
            this.usuariosComponent.disableEdit();

            Backbone.on(this.usuariosComponent.componentId + '-toolbar-add', function() {
                var selection = new SelectionController({"componentId":"usuariosComponent"});
                App.Utils.getComponentList('estudianteComponent', function(componentName, model) {
                    if (model.models.length == 0) {
                        alert('There is no Usuarioss to select.');
                    } else {
                        selection.showSelectionList({list: model, name: 'name', title: 'Usuarios List'});
                    }
                    ;
                });
            });

            Backbone.on('usuariosComponent-post-selection', function(models) {
            	self.usuariosComponent.addRecords(models);
            	self.usuariosComponent.render();
            });

            this.configToolbar(this.problemasComponent,false);
            this.problemasComponent.disableEdit();

            Backbone.on(this.problemasComponent.componentId + '-toolbar-add', function() {
                var selection = new SelectionController({"componentId":"problemasComponent"});
                App.Utils.getComponentList('problemaComponent', function(componentName, model) {
                    if (model.models.length == 0) {
                        alert('There is no Problemass to select.');
                    } else {
                        selection.showSelectionList({list: model, name: 'name', title: 'Problemas List'});
                    }
                    ;
                });
            });

            Backbone.on('problemasComponent-post-selection', function(models) {
            	self.problemasComponent.addRecords(models);
            	self.problemasComponent.render();
            });

		},
        renderChilds: function(params) {
            var self = this;
            
            var options = {
                success: function() {
                	self.tabs.render(self.tabsElement);

					self.usuariosComponent.clearCache();
					self.usuariosComponent.setRecords(self.model.get('listusuarios'));
					self.usuariosComponent.render(self.tabs.getTabHtmlId('usuarios'));

					self.problemasComponent.clearCache();
					self.problemasComponent.setRecords(self.model.get('listproblemas'));
					self.problemasComponent.render(self.tabs.getTabHtmlId('problemas'));

                    $('#'+self.tabsElement).show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'unidadDeRed-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.UnidadDeRedMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.UnidadDeRedMasterModel();
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

    return App.Component._UnidadDeRedMasterComponent;
});