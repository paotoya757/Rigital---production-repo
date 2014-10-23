define(['component/_mySQLMasterComponent'],function(_MySQLMasterComponent) {
    App.Component.MySQLMasterComponent = _MySQLMasterComponent.extend({
		postInit: function(){
			//Escribir en este servicio las instrucciones que desea ejecutar al inicializar el componente
		}
    });

    return App.Component.MySQLMasterComponent;
});