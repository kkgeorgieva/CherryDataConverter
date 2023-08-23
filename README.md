# CherryDataConvert

Application used to convert from one file type to another. 

# Usage

java -jar target\CherryDataConvert-1.0-SNAPSHOT.jar pathToInputFile pathToOutputFile pathToConfigFile inputFileFormat outputFileFormat

# Configuration file

The configuration file must adhere to the configTemplate.yaml inside the project. The structure is as follows:

    input:   
     parameter1 : value1  
    output:  
     parameter2 : value2
 
Each section can have multiple parameters and multiple subsections. The input configuration is passed to the Reader classes, while the output configuration is passed to the Writer classes. There are no mandatory parameters that must be included, and the default configuration file just has an empty input and output section. Handling of the parameters is done inside each Reader and Writer class. 

# Extending with your own formats

You can create your own InputFactory, OutputFactory, Decoder, Encoder implementations to extend the functionality of the application.

1. First, you need to create a new Decoder and Encoder classes that implement the Decoder/Encoder interface.
    1. These classes will be called by your factory implementations, so you can name them however you want. You can even make them as internal classes to the factories.
2. Implement the methods inherited from the interface.
3. Then you need to create InputFactory and OutputFactory that implement the InputFactoryInterface/OutputFactoryInterface.
    1. The name of your classes **MUST** be of the format: **capitalsFileType + InputFactory/OutputFactory**. For example, to create an **XML** InputFactory and OutputFactory, you need to name your classes **XMLInputFactory** and **XMLOutputFactory**.
4. Implement the methods inherited from the interface.
5. Put your classes in the respective **com.dxc.cherry.converter.input** or **com.dxc.cherry.converter.output** package.
6. When running the application, make sure to type the inputFileFormat and outputFileFormat the same way you named your factory classes. For example, if you created an **XMLInputFactory**, and want to convert from XML to another type, make sure to enter **XML** as the input type.
6. If your Decoder/Encoder class requires any parameters from the configuration file, these will be parsed and passed to the decoder/encoder. It is up to your class to do anything with them. It is also your class' task to require parameters in the config file, and throw an exception if those are not included.
