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
define(['component/_contenedorWebComponent'], function() {
    App.Component.ContenedorWebComponent = App.Component._ContenedorWebComponent.extend({
        postInit: function(){
            var self = this; 

            Backbone.on(self.componentId + '-contenedorWeb-display-textfield', function(params) {
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
                this.toolbarComponent.showButton('desactivar');
                this.toolbarComponent.showButton('create');
                this.toolbarComponent.showButton('refresh');
                this.toolbarComponent.showButton('print');
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


                                    this.componentController.search();
            },
            execSearch: function(){
            this.toolbarComponent.showButton('create');
            this.toolbarComponent.showButton('refresh');
            this.toolbarComponent.showButton('print');
            this.toolbarComponent.showButton('search');
            this.toolbarComponent.hideButton('cancel-search');
            this.toolbarComponent.hideButton('exec-search');
            this.componentController.contenedorwebsearch(this.list,this);           
            this.toolbarComponent.render();
            }


    });
    return App.Component.ContenedorWebComponent;
});