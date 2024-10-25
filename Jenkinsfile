pipeline {
    agent any 
    stages {
		
		 stage ('Build') {
            steps {
                bat'mvn clean test -Dcucumber.filter.tags="@demoTwo"' 
            }
		
	        }
    }
}
