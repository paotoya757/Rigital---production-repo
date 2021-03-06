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
define(['controller/_workstationController','delegate/workstationDelegate'], function() {
    App.Controller.WorkstationController = App.Controller._WorkstationController.extend({
postInit:function(){

            this.currentTemplate = _.template($('#workstationSearchTemplate').html());
        },

        setCurrentTemplate:function(templateName){
            var self = this;
            self.currentTemplate = _.template($('#'+templateName).html());
        },
        setVisibility : function(params){
            var txt_id = params.textInputId ;
            var style = $(''+txt_id).attr("style");
            var sp = style.split(": ");
            var btn_id = '#btn_'+(txt_id.split("#")[1]).replace("fg-","");
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
           
            this.$el.slideUp("fast", function() {
                self.$el.html(self.currentTemplate({workstation: self.currentModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        },
        search:function(){
            if (App.Utils.eventExists(this.componentId + '-' +'instead-workstation-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-workstation-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-workstation-create', {view: this});
                this.currentModel = new this.modelClass({componentId: this.componentId});
                this._render();
                Backbone.trigger(this.componentId + '-' + 'post-workstation-create', {view: this});
            }
        },
        workstationsearch: function(callback,context){
            var self = this;
            var model = $('#' + this.componentId + '-workstationForm').serializeObject();
            this.currentModel.set(model);
            var delegate = new App.Delegate.WorkstationDelegate();
            delegate.search(self.currentModel, function (data) {
                self.currentList.reset(data.records);
                callback.call(context,{data: self.currentList, page: 1, pages: 1, totalRecords: self.currentList.lenght})
            }, function (data) {
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'workstation-search', view: self, id: '', data: data, error: 'Error in workstation search'});
            });
        }
        
    });
    return App.Controller.WorkstationController;
}); 