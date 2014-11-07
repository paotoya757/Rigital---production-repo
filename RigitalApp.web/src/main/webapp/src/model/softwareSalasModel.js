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
define(['model/_softwareSalasModel'], function() {
    App.Model.SoftwareSalasModel = App.Model._SoftwareSalasModel.extend({

            defaults:{
                fechaCreacion2:'',
            },
 		validate: function(attrs,options){
            var validationMessage = "";
            if(!attrs.name){
                validationMessage = "El nombre no puede estar vacio";
            }
            if(validationMessage.length>0){
               return validationMessage;
            }
        }

    });

    App.Model.SoftwareSalasList = App.Model._SoftwareSalasList.extend({
        model: App.Model.SoftwareSalasModel
    });

    return  App.Model.SoftwareSalasModel;

});