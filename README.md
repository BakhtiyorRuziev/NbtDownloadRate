# NbtDownloadRate
Application for downloading currency rate from National Bank of Tajikistan.

For running need:

* Configure mongoDb:
    Change this string in *src/main/resources/context.xml*:
    
    ```xml
        
        <mongo:mongo host="127.0.0.1" port="27017"/>
        <mongo:db-factory dbname="rates2"/>

    ```
* gradlew install
* gradlew run
    





