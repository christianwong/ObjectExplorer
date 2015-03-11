# README #

### Usage ###

Run demo (release X) without *filename* as argument:
>     java -cp "ObjectExplorer_release_X.jar;lib/*" com.logexplorer.app.ObjectExplorerLauncher

Run release X with this command:
>     java -cp "ObjectExplorer_release_X.jar;lib/*" com.logexplorer.app.ObjectExplorerLauncher filename

Debug release X with this command:
>     java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=1044 -cp "ObjectExplorer_release_X.jar;lib/*"  filename

Run release X with logs and saving log to file
>     @echo off
>     
>     set _my_datetime=%date%_%time%
>     set _my_datetime=%_my_datetime: =_%
>     set _my_datetime=%_my_datetime::=%
>     set _my_datetime=%_my_datetime:/=_%
>     set _my_datetime=%_my_datetime:.=_%
>     
>     set filename=C:\path_to_log\%filename%%_my_datetime%.log
>     
>     echo %1 >> %filename%
>     
>     java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=1044 -cp "C:\path_to_jar\ObjectExplorer_release_2.3.2.jar;C:\path_to_lib\lib\*" "com.logexplorer.app.ObjectExplorerLauncher" %1 1>> %filename% 2>>&1


### Screenshoot ###

![Alt text](https://bytebucket.org/christianwong/objectexplorer/raw/aff5a4abd112a1bab6cdf9d2d752372fbc5e6ead/doc/img/ObjectX_2.3.3.png?token=4e572927e48b8485f7a9706a5fbc77633005eab5)

