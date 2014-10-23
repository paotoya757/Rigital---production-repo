define(['controller/selectionController', 'model/cacheModel', 'model/encargadoMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/encargadoComponent',
 'component/wikiComponent', 'component/maquinaVirtualComponent', 'component/unidadDeRedComponent', 'component/repositorioComponent', 'component/contenedorWebComponent', 'component/softwareSalasComponent', 'component/sQLDevComponent', 'component/mySQLComponent'],
 function(SelectionController, CacheModel, EncargadoMasterModel, CRUDComponent, TabController, EncargadoComponent,
 wikiComponent, maquinaVirtualComponent, unidadDeRedComponent, repositorioComponent, contenedorWebComponent, softwareSalasComponent, sQLDevComponent, mySQLComponent) {
    App.Component._EncargadoMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('encargadoMaster');
            App.Model.EncargadoMasterModel.prototype.urlRoot = this.configuration.context;
            this.componentId = App.Utils.randomInteger();
            
            this.masterComponent = new EncargadoComponent();
            this.masterComponent.initialize();
            
            this.childComponents = [];
			
			this.initializeChildComponents();
            
            Backbone.on(this.masterComponent.componentId + '-post-encargado-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-post-encargado-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-pre-encargado-list', function() {
                self.hideChilds();
            });
            Backbone.on('encargado-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'encargado-master-save', view: self, message: error});
            });
            Backbone.on(this.masterComponent.componentId + '-instead-encargado-save', function(params) {
                self.model.set('encargadoEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }

				App.Utils.fillCacheList(
					'wiki',
					self.model,
					self.wikiComponent.getDeletedRecords(),
					self.wikiComponent.getUpdatedRecords(),
					self.wikiComponent.getCreatedRecords()
				);

				App.Utils.fillCacheList(
					'maquinaVirtual',
					self.model,
					self.maquinaVirtualComponent.getDeletedRecords(),
					self.maquinaVirtualComponent.getUpdatedRecords(),
					self.maquinaVirtualComponent.getCreatedRecords()
				);

				App.Utils.fillCacheList(
					'unidadDeRed',
					self.model,
					self.unidadDeRedComponent.getDeletedRecords(),
					self.unidadDeRedComponent.getUpdatedRecords(),
					self.unidadDeRedComponent.getCreatedRecords()
				);

				App.Utils.fillCacheList(
					'repositorio',
					self.model,
					self.repositorioComponent.getDeletedRecords(),
					self.repositorioComponent.getUpdatedRecords(),
					self.repositorioComponent.getCreatedRecords()
				);

				App.Utils.fillCacheList(
					'contenedorWeb',
					self.model,
					self.contenedorWebComponent.getDeletedRecords(),
					self.contenedorWebComponent.getUpdatedRecords(),
					self.contenedorWebComponent.getCreatedRecords()
				);

				App.Utils.fillCacheList(
					'softwareSalas',
					self.model,
					self.softwareSalasComponent.getDeletedRecords(),
					self.softwareSalasComponent.getUpdatedRecords(),
					self.softwareSalasComponent.getCreatedRecords()
				);

				App.Utils.fillCacheList(
					'sQLDev',
					self.model,
					self.sQLDevComponent.getDeletedRecords(),
					self.sQLDevComponent.getUpdatedRecords(),
					self.sQLDevComponent.getCreatedRecords()
				);

				App.Utils.fillCacheList(
					'mySQL',
					self.model,
					self.mySQLComponent.getDeletedRecords(),
					self.mySQLComponent.getUpdatedRecords(),
					self.mySQLComponent.getCreatedRecords()
				);

                self.model.save({}, {
                    success: function() {
                        Backbone.trigger(self.masterComponent.componentId + '-' + 'post-encargado-save', {view: self, model : self.model});
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'encargado-master-save', view: self, error: error});
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
                {label: "Wiki", name: "wiki", enable: true},
                {label: "Maquina Virtual", name: "maquinaVirtual", enable: true},
                {label: "Unidad De Red", name: "unidadDeRed", enable: true},
                {label: "Repositorio", name: "repositorio", enable: true},
                {label: "Contenedor Web", name: "contenedorWeb", enable: true},
                {label: "Software Salas", name: "softwareSalas", enable: true},
                {label: "S Q L Dev", name: "sQLDev", enable: true},
                {label: "My S Q L", name: "mySQL", enable: true}
			]});
			this.tabs = new TabController({model: this.tabModel});

			this.wikiComponent = new wikiComponent();
            this.wikiComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.wikiComponent);

			this.maquinaVirtualComponent = new maquinaVirtualComponent();
            this.maquinaVirtualComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.maquinaVirtualComponent);

			this.unidadDeRedComponent = new unidadDeRedComponent();
            this.unidadDeRedComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.unidadDeRedComponent);

			this.repositorioComponent = new repositorioComponent();
            this.repositorioComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.repositorioComponent);

			this.contenedorWebComponent = new contenedorWebComponent();
            this.contenedorWebComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.contenedorWebComponent);

			this.softwareSalasComponent = new softwareSalasComponent();
            this.softwareSalasComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.softwareSalasComponent);

			this.sQLDevComponent = new sQLDevComponent();
            this.sQLDevComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.sQLDevComponent);

			this.mySQLComponent = new mySQLComponent();
            this.mySQLComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.mySQLComponent);

            var self = this;
            
            this.configToolbar(this.wikiComponent,false);
            this.wikiComponent.disableEdit();

            Backbone.on(this.wikiComponent.componentId + '-toolbar-add', function() {
                var selection = new SelectionController({"componentId":"wikiComponent"});
                App.Utils.getComponentList('wikiComponent', function(componentName, model) {
                    if (model.models.length == 0) {
                        alert('There is no Wikis to select.');
                    } else {
                        selection.showSelectionList({list: model, name: 'name', title: 'Wiki List'});
                    }
                    ;
                });
            });

            Backbone.on('wikiComponent-post-selection', function(models) {
            	self.wikiComponent.addRecords(models);
            	self.wikiComponent.render();
            });

            this.configToolbar(this.maquinaVirtualComponent,false);
            this.maquinaVirtualComponent.disableEdit();

            Backbone.on(this.maquinaVirtualComponent.componentId + '-toolbar-add', function() {
                var selection = new SelectionController({"componentId":"maquinaVirtualComponent"});
                App.Utils.getComponentList('maquinaVirtualComponent', function(componentName, model) {
                    if (model.models.length == 0) {
                        alert('There is no Maquina Virtuals to select.');
                    } else {
                        selection.showSelectionList({list: model, name: 'name', title: 'Maquina Virtual List'});
                    }
                    ;
                });
            });

            Backbone.on('maquinaVirtualComponent-post-selection', function(models) {
            	self.maquinaVirtualComponent.addRecords(models);
            	self.maquinaVirtualComponent.render();
            });

            this.configToolbar(this.unidadDeRedComponent,false);
            this.unidadDeRedComponent.disableEdit();

            Backbone.on(this.unidadDeRedComponent.componentId + '-toolbar-add', function() {
                var selection = new SelectionController({"componentId":"unidadDeRedComponent"});
                App.Utils.getComponentList('unidadDeRedComponent', function(componentName, model) {
                    if (model.models.length == 0) {
                        alert('There is no Unidad De Reds to select.');
                    } else {
                        selection.showSelectionList({list: model, name: 'name', title: 'Unidad De Red List'});
                    }
                    ;
                });
            });

            Backbone.on('unidadDeRedComponent-post-selection', function(models) {
            	self.unidadDeRedComponent.addRecords(models);
            	self.unidadDeRedComponent.render();
            });

            this.configToolbar(this.repositorioComponent,false);
            this.repositorioComponent.disableEdit();

            Backbone.on(this.repositorioComponent.componentId + '-toolbar-add', function() {
                var selection = new SelectionController({"componentId":"repositorioComponent"});
                App.Utils.getComponentList('repositorioComponent', function(componentName, model) {
                    if (model.models.length == 0) {
                        alert('There is no Repositorios to select.');
                    } else {
                        selection.showSelectionList({list: model, name: 'name', title: 'Repositorio List'});
                    }
                    ;
                });
            });

            Backbone.on('repositorioComponent-post-selection', function(models) {
            	self.repositorioComponent.addRecords(models);
            	self.repositorioComponent.render();
            });

            this.configToolbar(this.contenedorWebComponent,false);
            this.contenedorWebComponent.disableEdit();

            Backbone.on(this.contenedorWebComponent.componentId + '-toolbar-add', function() {
                var selection = new SelectionController({"componentId":"contenedorWebComponent"});
                App.Utils.getComponentList('contenedorWebComponent', function(componentName, model) {
                    if (model.models.length == 0) {
                        alert('There is no Contenedor Webs to select.');
                    } else {
                        selection.showSelectionList({list: model, name: 'name', title: 'Contenedor Web List'});
                    }
                    ;
                });
            });

            Backbone.on('contenedorWebComponent-post-selection', function(models) {
            	self.contenedorWebComponent.addRecords(models);
            	self.contenedorWebComponent.render();
            });

            this.configToolbar(this.softwareSalasComponent,false);
            this.softwareSalasComponent.disableEdit();

            Backbone.on(this.softwareSalasComponent.componentId + '-toolbar-add', function() {
                var selection = new SelectionController({"componentId":"softwareSalasComponent"});
                App.Utils.getComponentList('softwareSalasComponent', function(componentName, model) {
                    if (model.models.length == 0) {
                        alert('There is no Software Salass to select.');
                    } else {
                        selection.showSelectionList({list: model, name: 'name', title: 'Software Salas List'});
                    }
                    ;
                });
            });

            Backbone.on('softwareSalasComponent-post-selection', function(models) {
            	self.softwareSalasComponent.addRecords(models);
            	self.softwareSalasComponent.render();
            });

            this.configToolbar(this.sQLDevComponent,false);
            this.sQLDevComponent.disableEdit();

            Backbone.on(this.sQLDevComponent.componentId + '-toolbar-add', function() {
                var selection = new SelectionController({"componentId":"sQLDevComponent"});
                App.Utils.getComponentList('sQLDevComponent', function(componentName, model) {
                    if (model.models.length == 0) {
                        alert('There is no S Q L Devs to select.');
                    } else {
                        selection.showSelectionList({list: model, name: 'name', title: 'S Q L Dev List'});
                    }
                    ;
                });
            });

            Backbone.on('sQLDevComponent-post-selection', function(models) {
            	self.sQLDevComponent.addRecords(models);
            	self.sQLDevComponent.render();
            });

            this.configToolbar(this.mySQLComponent,false);
            this.mySQLComponent.disableEdit();

            Backbone.on(this.mySQLComponent.componentId + '-toolbar-add', function() {
                var selection = new SelectionController({"componentId":"mySQLComponent"});
                App.Utils.getComponentList('mySQLComponent', function(componentName, model) {
                    if (model.models.length == 0) {
                        alert('There is no My S Q Ls to select.');
                    } else {
                        selection.showSelectionList({list: model, name: 'name', title: 'My S Q L List'});
                    }
                    ;
                });
            });

            Backbone.on('mySQLComponent-post-selection', function(models) {
            	self.mySQLComponent.addRecords(models);
            	self.mySQLComponent.render();
            });

		},
        renderChilds: function(params) {
            var self = this;
            
            var options = {
                success: function() {
                	self.tabs.render(self.tabsElement);

					self.wikiComponent.clearCache();
					self.wikiComponent.setRecords(self.model.get('listwiki'));
					self.wikiComponent.render(self.tabs.getTabHtmlId('wiki'));

					self.maquinaVirtualComponent.clearCache();
					self.maquinaVirtualComponent.setRecords(self.model.get('listmaquinaVirtual'));
					self.maquinaVirtualComponent.render(self.tabs.getTabHtmlId('maquinaVirtual'));

					self.unidadDeRedComponent.clearCache();
					self.unidadDeRedComponent.setRecords(self.model.get('listunidadDeRed'));
					self.unidadDeRedComponent.render(self.tabs.getTabHtmlId('unidadDeRed'));

					self.repositorioComponent.clearCache();
					self.repositorioComponent.setRecords(self.model.get('listrepositorio'));
					self.repositorioComponent.render(self.tabs.getTabHtmlId('repositorio'));

					self.contenedorWebComponent.clearCache();
					self.contenedorWebComponent.setRecords(self.model.get('listcontenedorWeb'));
					self.contenedorWebComponent.render(self.tabs.getTabHtmlId('contenedorWeb'));

					self.softwareSalasComponent.clearCache();
					self.softwareSalasComponent.setRecords(self.model.get('listsoftwareSalas'));
					self.softwareSalasComponent.render(self.tabs.getTabHtmlId('softwareSalas'));

					self.sQLDevComponent.clearCache();
					self.sQLDevComponent.setRecords(self.model.get('listsQLDev'));
					self.sQLDevComponent.render(self.tabs.getTabHtmlId('sQLDev'));

					self.mySQLComponent.clearCache();
					self.mySQLComponent.setRecords(self.model.get('listmySQL'));
					self.mySQLComponent.render(self.tabs.getTabHtmlId('mySQL'));

                    $('#'+self.tabsElement).show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'encargado-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.EncargadoMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.EncargadoMasterModel();
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

    return App.Component._EncargadoMasterComponent;
});