
pipeline
{

    agent
    {
        docker { image 'maven:3.6.0-jdk-11' }
    }

    environment {
        SONAR_CONFIG_NAME = 'sonarqube-service'
        SONAR_HOST_URL    = 'http://localhost:9000'
    }

    stages
    {

        stage( "Unit Tests with Software Profiling" )
        {
            steps
            {
                withSonarQubeEnv( 'sonarqube-service' )
                {
                    sh 'mvn clean package sonar:sonar'
                }
            }
        }

        stage( "Wait 4 Quality Judgement" )
        {
            steps
            {
                timeout( time: 2, unit: 'MINUTES' )
                {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

    }

}
