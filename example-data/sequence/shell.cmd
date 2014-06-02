java -classpath ../../fudannlp-1.1.jar;../../lib/commons-cli-1.2.jar;../../lib/trove.jar; edu.fudan.nlp.tag.Tagger -train template train.txt model
java -classpath ../../fudannlp-1.1.jar;../../lib/commons-cli-1.2.jar;../../lib/trove.jar;  edu.fudan.nlp.tag.Tagger model test.txt result.txt
@echo delete model file
del model
@echo press any key to delete result.txt file
pause>nul
del result.txt