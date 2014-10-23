define(['component/_sQLDevMasterComponent'],function(_SQLDevMasterComponent) {
    App.Component.SQLDevMasterComponent = _SQLDevMasterComponent.extend({
		postInit: function(){
			//Escribir en este servicio las instrucciones que desea ejecutar al inicializar el componente
		}
    });

    return App.Component.SQLDevMasterComponent;
});