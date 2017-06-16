@echo off
rem Generation of GLanguage's lexical and syntactic analyzers
rem

java -jar "JFlex.jar" slang.l


yacc -Jclass=SlangTab -Jsemantic=SemanticType slang.y

rem -t class_name : generate class_name.java containing the "tokens"
rem -x : one routine per semantical action
rem -o file_name : output file name
pause
