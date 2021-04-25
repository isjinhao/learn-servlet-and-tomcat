keytool -genkey -alias clientprivate -keystore client.private -storetype JKS -keyalg rsa -dname "CN=, OU=, O=, L=, S=, C=" -storepass clientstorepw -keypass clientkeypw
keytool -export -alias clientprivate -keystore client.private -file tempclient.key -storepass clientstorepw
keytool -import -noprompt -alias clientpublic -keystore client.public -file tempclient.key -storepass clientpublicpw


keytool -genkey -alias serverprivate -keystore server.private -storetype JKS -keyalg rsa -dname "CN=, OU=, O=, L=, S=, C=" -storepass serverstorepw -keypass serverkeypw
keytool -export -alias serverprivate -keystore server.private -file tempserver.key -storepass serverstorepw
keytool -import -noprompt -alias serverpublic -keystore server.public -file tempserver.key -storepass serverpublicpw

pause
