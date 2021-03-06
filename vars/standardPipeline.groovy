def deploy(url) {
     echo "INFO: ${url}"
	 
	 timeout(time: 3, unit: 'MINUTES') {
		// you can use the commented line if u have specific user group who CAN ONLY approve
		input message:'Aprobar el despliegue?', submitter: 'it-ops'
		//input message: 'Aprobar el despliegue?'
     }
	 
	 //Descarga la configuración de despliegue a producción desde git
	 checkout([$class: 'GitSCM', 
						branches: [[name: '*/master']], 
						doGenerateSubmoduleConfigurations: false, 
						extensions: [[$class: 'RelativeTargetDirectory', 
							relativeTargetDir: 'KitBasicoAutomApp-Ops']], 
						submoduleCfg: [], 
						userRemoteConfigs: [[url: 'https://github.com/mauro2357/KitBasicoAutomApp-Ops.git']]])
						
     bat 'mkdir "KitBasicoAutomApp/build/libs/config"'
					bat 'xcopy "KitBasicoAutomApp-Ops/config" "KitBasicoAutomApp/build/libs/config"'
					//bat "deploy-bd.bat"
					//bat "deploy-app.bat"
	 
	 // Clean the files after doing deployment
	 bat 'rmdir "KitBasicoAutomApp-Ops" /S /Q'
}
