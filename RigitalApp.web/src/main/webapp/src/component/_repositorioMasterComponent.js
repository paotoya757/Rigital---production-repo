define(['controller/selectionController', 'model/cacheModel', 'model/repositorioMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/repositorioComponent',
 'component/estudianteComponent', 'component/problemaComponent'],
 function(SelectionController, CacheModel, RepositorioMasterModel, CRUDComponent, TabController, RepositorioComponent,
 usuariosQueAccedenComponent, problemasComponent) {
    App.Component._RepositorioMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('repositorioMaster');
            App.Model.RepositorioMasterModel.prototype.urlRoot = this.configuration.context;
            this.componentId = App.Utils.randomInteger();
            
            this.masterComponent = new RepositorioComponent();
            this.masterComponent.initialize();
            
            this.childComponents = [];
			
			this.initializeChildComponents();
            
            Backbone.on(this.masterComponent.componentId + '-post-repositorio-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-post-repositorio-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-pre-repositorio-list', function() {
                self.hideChilds();
            });
            Backbone.on('repositorio-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'repositorio-master-save', view: self, message: error});
            });
            Backbone.on(this.masterComponent.componentId + '-instead-repositorio-save', function(params) {
                self.model.set('repositorioEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }

				App.Utils.fillCacheList(
					'usuariosQueAcceden',
					self.model,
					self.usuariosQueAccedenComponent.getDeletedRecords(),
					self.usuariosQueAccedenComponent.getUpdatedRecords(),
					self.usuariosQueAccedenComponent.getCreatedRecords()
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
                        Backbone.trigger(self.masterComponent.componentId + '-' + 'post-repositorio-save', {view: self, model : self.model});
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'repositorio-master-save', view: self, error: error});
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
                {label: "Usuarios Que Acceden", name: "usuariosQueAcceden", enable: true},
                {label: "Problemas", name: "problemas", enable: true}
			]});
			this.tabs = new TabController({model: this.tabModel});

			this.usuariosQueAccedenComponent = new usuariosQueAccedenComponent();
            this.usuariosQueAccedenComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.usuariosQueAccedenComponent);

			this.problemasComponent = new problemasComponent();
            this.problemasComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.problemasComponent);

            var self = this;
            
            this.configToolbar(this.usuariosQueAccedenComponent,false);
            this.usuariosQueAccedenComponent.disableEdit();

            Backbone.on(this.usuariosQueAccedenComponent.componentId + '-toolbar-add', function() {
                var selection = new SelectionController({"componentId":"usuariosQueAccedenComponent"});
                App.Utils.getComponentList('estudianteComponent', function(componentName, model) {
                    if (model.models.length == 0) {
                        alert('There is no Usuarios Que Accedens to select.');
                    } else {
                        selection.showSelectionList({list: model, name: 'name', title: 'Usuarios Que Acceden List'});
                    }
                    ;
                });
            });

            Backbone.on('usuariosQueAccedenComponent-post-selection', function(models) {
            	self.usuariosQueAccedenComponent.addRecords(models);
            	self.usuariosQueAccedenComponent.render();
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

					self.usuariosQueAccedenComponent.clearCache();
					self.usuariosQueAccedenComponent.setRecords(self.model.get('listusuariosQueAcceden'));
					self.usuariosQueAccedenComponent.render(self.tabs.getTabHtmlId('usuariosQueAcceden'));

					self.problemasComponent.clearCache();
					self.problemasComponent.setRecords(self.model.get('listproblemas'));
					self.problemasComponent.render(self.tabs.getTabHtmlId('problemas'));

                    $('#'+self.tabsElement).show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'repositorio-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.RepositorioMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.RepositorioMasterModel();
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

    return App.Component._RepositorioMasterComponent;
});