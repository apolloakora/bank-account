
pipeline
{

    agent
    {
        docker { image 'maven:3.6.0-jdk-11' }
    }

    stages
    {

        stage( "Unit Tests with Software Profiling with SonarQube" )
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
