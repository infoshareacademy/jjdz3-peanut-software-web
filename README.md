# jjdz3-peanut-software-web


To install the new version of external jar package in local-maven-repo, at the root of the project execute:
mvn deploy:deploy-file -DgroupId=com.infoshareacademy -DartifactId=peanut.medicine -Dversion=1.0 -Durl=file:./local-maven-repo/ -DrepositoryId=local-maven-repo -DupdateReleaseInfo=true -Dfile=./libs/com.infoshareacademy/peanut.medicine/1.0/peanut.medicine-1.0-jar-with-dependencies.jar
(only change number of version)