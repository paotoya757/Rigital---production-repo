define(['controller/selectionController', 'model/cacheModel', 'model/sQLDevMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/sQLDevComponent',
 'component/estudianteComponent', 'component/problemaComponent'],
 function(SelectionController, CacheModel, SQLDevMasterModel, CRUDComponent, TabController, SQLDevComponent,
 estudiantesConAccesoComponent, problemaComponent) {
    App.Component._SQLDevMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('sQLDevMaster');
            App.Model.SQLDevMasterModel.prototype.urlRoot = this.configuration.context;
            this.componentId = App.Utils.randomInteger();
            
            this.masterComponent = new SQLDevComponent();
            this.masterComponent.initialize();
            
            this.childComponents = [];
			
			this.initializeChildComponents();
            
            Backbone.on(this.masterComponent.componentId + '-post-sQLDev-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-post-sQLDev-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-pre-sQLDev-list', function() {
                self.hideChilds();
            });
            Backbone.on('sQLDev-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'sQLDev-master-save', view: self, message: error});
            });
            Backbone.on(this.masterComponent.componentId + '-instead-sQLDev-save', function(params) {
                self.model.set('sQLDevEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }

				App.Utils.fillCacheList(
					'estudiantesConAcceso',
					self.model,
					self.estudiantesConAccesoComponent.getDeletedRecords(),
					self.estudiantesConAccesoComponent.getUpdatedRecords(),
					self.estudiantesConAccesoComponent.getCreatedRecords()
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
                        Backbone.trigger(self.masterComponent.componentId + '-' + 'post-sQLDev-save', {view: self, model : self.model});
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'sQLDev-master-save', view: self, error: error});
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
                {label: "Estudiantes Con Acceso", name: "estudiantesConAcceso", enable: true},
                {label: "Problema", name: "problema", enable: true}
			]});
			this.tabs = new TabController({model: this.tabModel});

			this.estudiantesConAccesoComponent = new estudiantesConAccesoComponent();
            this.estudiantesConAccesoComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.estudiantesConAccesoComponent);

			this.problemaComponent = new problemaComponent();
            this.problemaComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.problemaComponent);

            var self = this;
            
            this.configToolbar(this.estudiantesConAccesoComponent,false);
            this.estudiantesConAccesoComponent.disableEdit();

            Backbone.on(this.estudiantesConAccesoComponent.componentId + '-toolbar-add', function() {
                var selection = new SelectionController({"componentId":"estudiantesConAccesoComponent"});
                App.Utils.getComponentList('estudianteComponent', function(componentName, model) {
                    if (model.models.length == 0) {
                        alert('There is no Estudiantes Con Accesos to select.');
                    } else {
                        selection.showSelectionList({list: model, name: 'name', title: 'Estudiantes Con Acceso List'});
                    }
                    ;
                });
            });

            Backbone.on('estudiantesConAccesoComponent-post-selection', function(models) {
            	self.estudiantesConAccesoComponent.addRecords(models);
            	self.estudiantesConAccesoComponent.render();
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

					self.estudiantesConAccesoComponent.clearCache();
					self.estudiantesConAccesoComponent.setRecords(self.model.get('listestudiantesConAcceso'));
					self.estudiantesConAccesoComponent.render(self.tabs.getTabHtmlId('estudiantesConAcceso'));

					self.problemaComponent.clearCache();
					self.problemaComponent.setRecords(self.model.get('listproblema'));
					self.problemaComponent.render(self.tabs.getTabHtmlId('problema'));

                    $('#'+self.tabsElement).show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'sQLDev-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.SQLDevMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.SQLDevMasterModel();
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

    return App.Component._SQLDevMasterComponent;
});