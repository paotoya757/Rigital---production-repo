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
define(['component/_problemaComponent'], function() {
    App.Component.ProblemaComponent = App.Component._ProblemaComponent.extend({
        postInit: function(){
            var self = this; 
            //Start: searchRelated
            // Cambio - siguiente linea : '-<miComponente>-display-textfield"'
            Backbone.on(self.componentId + '-problema-display-textfield', function(params) {
                self.componentController.setVisibility(params);
            });
            
			this.toolbarComponent.addButton({
				name: 'exec-search',
				displayName: 'Search',
				icon: 'glyphicon-search',
				show: false
			},
			this.execSearch,
			this);
			this.toolbarComponent.addButton({
				name: 'cancel-search',
				displayName: 'Cancel',
				icon: 'glyphicon-remove-sign',
				show: false
			},
			function(){
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
                        // Cambio - siguiente linea : setCurrentTemplate('<miComponente>')
                        this.componentController.setCurrentTemplate('problemaSearchTemplate'); 
                        this.componentController.search();
		},
		execSearch: function(){
			this.toolbarComponent.showButton('create');
			this.toolbarComponent.showButton('refresh');
			this.toolbarComponent.showButton('print');
			this.toolbarComponent.showButton('search');
			this.toolbarComponent.hideButton('cancel-search');
			this.toolbarComponent.hideButton('exec-search');
                        this.componentController.problemasearch(this.list,this);
			this.toolbarComponent.render();
			
		}
                //end : searchRelated
                ,
    });
    return App.Component.ProblemaComponent;
});