define([], function() {
    App.Model._EncargadoMasterModel = Backbone.Model.extend({
     	initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('encargado-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.EncargadoModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.encargadoEntity,options);
            }
        }
    });

    App.Model._EncargadoMasterList = Backbone.Collection.extend({
        model: App.Model._EncargadoMasterModel,
        initialize: function() {
        }

    });
    return App.Model._EncargadoMasterModel;
    
});