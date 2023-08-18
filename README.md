# CherryDataConvert

Application used to convert from one file type to another. 

# Usage

java -jar target\CherryDataConvert-1.0-SNAPSHOT.jar pathToInputFile pathToOutputFile pathToConfigFile inputFileFormat outputFileFormat

# Configuration file

The configuration file must adhere to the configTemplate.yaml inside the project. The structure is as follows:

    input:   
     parameter1   
    output:  
     parameter2  
 
Each section can have multiple parameters and multiple subsections. The input configuration is passed to the Reader classes, while the output configuration is passed to the Writer classes. There are no mandatory parameters that must be included, and the default configuration file just has an empty input and output section. Handling of the parameters is done inside each Reader and Writer class. 

# Extending with your own formats

You can create your own Reader and Writer implementations to extend the functionality of the application.

1. First, create a new Reader and Writer classes that extend FileReaderProvider and FileWriterProvider.
  1. The name of your new classes **MUST** be of the format: **capitalsFileType + FileReader/FileWriter**. For example, to create an XML file reader and writer, you need      to name your classes **XMLFileReader** and **XMLFileWriter**.
2. Implement the methods inherited from the Provider abstract class.
3. Put your classes in the respective **com.dxc.file.reader** or **com.dxc.file.writer**.
4. When running the application, make sure to type the inputFileFormat and outputFileFormat the same way you named your classes. For example, if you created an **XMLFileReader**, and want to convert from XML to another type, make sure to enter **XML** as the input type.
5. If your reader/writer class requires any parameters from the configuration file, these will be parsed and passed to the reader/writer. It is up to your class to do anything with them. It is also your class' task to require parameters in the config file, and throw an exception if those are not included.
