/* ========================================================================
 * Copyright 2014 grupo
 *
 * Licensed under the MIT, The MIT License (MIT)
 * Copyright (c) 2014 grupo

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 * ========================================================================


Source generated by CrudMaker version 1.0.0.201410152247

*/
define(['component/_empleadoComponent'], function() {
    App.Component.EmpleadoComponent = App.Component._EmpleadoComponent.extend({
        postInit: function(){
            var self = this; 
            this.listComponent.addColumn('role', 'Rol');
            //Utilities
            this.toolbarComponent.removeMenu('utils');
            //Acciones
            this.toolbarComponent.removeMenu('actions');
            this.toolbarComponent.addMenu({
                name: 'actions',
                displayName: 'Acciones',
                show: true
            });
            //Agregar
            this.toolbarComponent.removeButton('create');
            /*this.toolbarComponent.addButton({
                name: 'create',
                icon: 'glyphicon-plus-sign',
                displayName: 'Agregar',
                show: true
            },
            this.create,
            this);*/
            //Guardar
            this.toolbarComponent.removeButton('save');
            this.toolbarComponent.addButton({
                name: 'save',
                icon: 'glyphicon-floppy-disk',
                displayName: 'Guardar',
                show: false
            },
            this.save,
            this);
            //Cancel
            this.toolbarComponent.removeButton('cancel');
            this.toolbarComponent.addButton({
                name: 'cancel',
                icon: 'glyphicon-remove-sign',
                displayName: 'Cancelar',
                show: false
            },
            this.cancel,
            this);
            //Refresh
            this.toolbarComponent.removeButton('refresh');
            this.toolbarComponent.addButton({
                name: 'refresh',
                icon: 'glyphicon-refresh',
                displayName: 'Refrescar',
                show: true,
            },
            this.refresh,
            this);
            //Print
            this.toolbarComponent.removeButton('print');
            //Editar
            this.listComponent.removeAction('edit');
            /*this.listComponent.addAction({
                name: 'edit',
                icon: '',
                displayName: 'Editar',
                show: true
            },
            this.edit,
            this);*/
            //Eliminar
            this.listComponent.removeAction('delete');
            /*this.listComponent.addAction({
                name: 'delete',
                icon: '',
                displayName: 'Eliminar',
                show: true
            },
            this.delete,
            this);*/
            
            self.componentController.checkUserRole(this);
                
           
            
            //Start: searchRelated
            // Cambio - siguiente linea : '-<miComponente>-display-textfield"'
            Backbone.on(self.componentId + '-empleado-display-textfield', function(params) {
                self.componentController.setVisibility(params);
            });
            
			this.toolbarComponent.addButton({
				name: 'exec-search',
				displayName: 'Buscar',
				icon: 'glyphicon-search',
				show: false
			},
			this.execSearch,
			this);
			this.toolbarComponent.addButton({
				name: 'cancel-search',
				displayName: 'Cancelar',
				icon: 'glyphicon-remove-sign',
				show: false
			},
			function(){
				this.toolbarComponent.showButton('create');
				this.toolbarComponent.showButton('refresh');
				this.toolbarComponent.showButton('search');
				this.toolbarComponent.hideButton('cancel-search');
				this.toolbarComponent.hideButton('exec-search');
				this.toolbarComponent.render();
				this.componentController.list(null, this.list, this);
			},
			this);
                        
        },
	search: function(){
            var self = this;
            this.toolbarComponent.hideButton('create');
            this.toolbarComponent.hideButton('save');
            this.toolbarComponent.hideButton('cancel');
            this.toolbarComponent.hideButton('print');
            this.toolbarComponent.hideButton('refresh');
            this.toolbarComponent.hideButton('search');
            this.toolbarComponent.showButton('exec-search');
            this.toolbarComponent.showButton('cancel-search');
            this.toolbarComponent.render();
            // Cambio - siguiente linea : setCurrentTemplate('<miComponente>')
            this.componentController.setCurrentTemplate('empleadoSearchTemplate'); 
            this.componentController.search();
	},
        execSearch: function(){
            this.toolbarComponent.showButton('create');
            this.toolbarComponent.showButton('refresh');
            this.toolbarComponent.showButton('search');
            this.toolbarComponent.hideButton('cancel-search');
            this.toolbarComponent.hideButton('exec-search');
            this.componentController.empleadosearch(this.list,this);
            this.toolbarComponent.render();		
	},
                //end : searchRelated
                //Funciones
        create: function() {
            this.toolbarComponent.hideButton('create');
            this.toolbarComponent.showButton('save');
            this.toolbarComponent.showButton('cancel');
            this.toolbarComponent.render();
            this.componentController.create();
        },
        save: function(params) {
            this.componentController.save();
            this.toolbarComponent.showButton('create');
            this.toolbarComponent.showButton('search');
            this.toolbarComponent.showButton('refresh');
        },
        cancel: function(params) {
            this.toolbarComponent.showButton('create');
            this.toolbarComponent.hideButton('save');
            this.toolbarComponent.hideButton('cancel');
            this.toolbarComponent.render();
            this.componentController.list(params, this.list, this);
        },
        refresh: function(params) {
            this.componentController.setPage(1);
            this.toolbarComponent.hideButton('save');
            this.toolbarComponent.hideButton('cancel');
            this.toolbarComponent.render();
            this.componentController.list(params, this.list, this);
            var messagesController = new App.Controller.MessageController({el: '#' + this.messageDomId});
            messagesController.showMessage('info', 'Informacion actualizada', true, 3);
        },
        edit: function(params) {
            this.toolbarComponent.hideButton('create');
            this.toolbarComponent.hideButton('search');
            this.toolbarComponent.showButton('save');
            this.toolbarComponent.showButton('cancel');
            this.toolbarComponent.render();
            this.componentController.edit(params);
        },
        delete: function(params) {
            this.componentController.destroy(params);
        },
        configUI: function() {
            this.listComponent.addColumn('name','Nombre');
            this.listComponent.addColumn('login','Login');
        }
    });
    return App.Component.EmpleadoComponent;
});