define(['component/_maquinaVirtualMasterComponent'],function(_MaquinaVirtualMasterComponent) {
    App.Component.MaquinaVirtualMasterComponent = _MaquinaVirtualMasterComponent.extend({
		postInit: function(){
			//Escribir en este servicio las instrucciones que desea ejecutar al inicializar el componente
		}
    });

    return App.Component.MaquinaVirtualMasterComponent;
});