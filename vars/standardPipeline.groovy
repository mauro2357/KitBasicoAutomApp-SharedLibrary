def deploy(url) {
     echo "INFO: ${url}"
	 
	 checkout([$class: 'GitSCM', 
						branches: [[name: '*/master']], 
						doGenerateSubmoduleConfigurations: false, 
						extensions: [[$class: 'RelativeTargetDirectory', 
							relativeTargetDir: 'KitBasicoAutomApp-Ops']], 
						submoduleCfg: [], 
						userRemoteConfigs: [[url: 'https://github.com/mauro2357/KitBasicoAutomApp-Ops.git']]]) 
	 
	 // Clean workspace after doing anything
	 //deleteDir()
}
