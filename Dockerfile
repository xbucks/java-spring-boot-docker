FROM openjdk:8
ADD target/users-mysl.war user-mysql.war
EXPOSE 9090
ENTRYPOINT ["java","-war","user-mysql.war"]