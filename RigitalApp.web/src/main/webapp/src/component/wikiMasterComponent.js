define(['component/_wikiMasterComponent'],function(_WikiMasterComponent) {
    App.Component.WikiMasterComponent = _WikiMasterComponent.extend({
		postInit: function(){
			//Escribir en este servicio las instrucciones que desea ejecutar al inicializar el componente
		}
    });

    return App.Component.WikiMasterComponent;
});