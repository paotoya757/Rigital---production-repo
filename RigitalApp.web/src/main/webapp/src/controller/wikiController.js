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
define(['controller/_wikiController','delegate/wikiDelegate'], function() {
    App.Controller.WikiController = App.Controller._WikiController.extend({
        postInit:function(){
            this.currentTemplate;
        },
        setCurrentTemplate:function(templateName){
            var self = this;
            self.currentTemplate = _.template($('#'+templateName).html());
        },
        setVisibility : function(params){
            var txt_id = params.textInputId ;
            var style = $(''+txt_id).attr("style");
            var sp = style.split(": ");
            var btn_id = '#btn_'+txt_id.split("#")[1];
            if( sp[1] == "hidden" ){
                $(btn_id).attr("class","btn btn-success");
                $(txt_id).attr("style","visibility: visible");
            }
            else{
                $(btn_id).attr("class","btn btn-primary");
                $(txt_id).attr("style","visibility: hidden");
            }
        },
        _render:function(){
            var self = this;
            // ojo: aqui hay que cambiar !
            this.$el.slideUp("fast", function() {
                self.$el.html(self.currentTemplate({wiki: self.currentModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
				    ,encargado: self.encargadoComponent
 
				}));
                self.$el.slideDown("fast");
            });
        },
        search:function(){
            if (App.Utils.eventExists(this.componentId + '-' +'instead-wiki-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-wiki-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-wiki-create', {view: this});
                this.currentModel = new this.modelClass({componentId: this.componentId});
                this._render();
                Backbone.trigger(this.componentId + '-' + 'post-wiki-create', {view: this});
            }
        }
    
     });
    return App.Controller.WikiController;
   });
    

