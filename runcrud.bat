       gradlew build
       if  "%ERRORLEVEL% "==0 go to rename
       echo.
       echo gradlew build has errors - breaking work
       goto fail
       :rename
       @rem here will be placed next comands
       goto end
       :fail
       echo.
       echo There were errors
       : end
       echo.
       echo work is finished