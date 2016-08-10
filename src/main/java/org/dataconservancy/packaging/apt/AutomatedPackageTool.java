/*
 * Copyright 2016 Johns Hopkins University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dataconservancy.packaging.apt;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;

/**
 * Created by jrm on 8/10/16.
 *
 * @author jrm
 */
public class AutomatedPackageTool {
    @Argument(multiValued = false, usage="URL to the registration to be packaged" )
    private String registrationUrl;

     /** Request for help/usage documentation */
    @Option(name = "-h", aliases = {"-help", "--help"}, usage = "print help message")
    private boolean help = false;

    /** the output directory for the package */
    @Option(name = "-o", aliases = {"-output", "--output"}, required = true, usage = "path to the directory where the package will be written")
    private static File outputLocation;

    /** the package name  **/
    @Option(name = "-n", aliases = {"-name", "--name"}, required = true, usage = "the name for the package")
    private static String packageName;

   /** other bag metadata properties file location */
    @Option(name = "-m", aliases = {"-metadata", "--metadata"}, usage = "the path to the metadata properties file for additional bag metadata")
    private static File bagMetadataFile;

    /** package generation parameters file */
    @Option(name = "-p", aliases = {"-parameters", "--parameters"}, usage = "the path to the package generation parameters file")
    private static File packageGenerationParametersFile;

    /** Requests the current version number of the cli application. */
	@Option(name = "-v", aliases = { "-version", "--version" }, usage = "print version information")
	private boolean version = false;


    public static void main(String[] args) {

        final AutomatedPackageTool application = new AutomatedPackageTool();

		CmdLineParser parser = new CmdLineParser(application);
		parser.setUsageWidth(80);

		try {
			parser.parseArgument(args);

			/* Handle general options such as help, version */
			if (application.help) {
				parser.printUsage(System.err);
				System.err.println();
				System.exit(0);
			} else if (application.version) {
				System.err.println(AutomatedPackageTool.class.getPackage()
						.getImplementationVersion());
				System.exit(0);
			}



            if (!outputLocation.exists() || !outputLocation.isDirectory()) {
                System.err.println("Supplied output file directory " + outputLocation.getCanonicalPath() + " does not exist or is not a directory.");
                System.exit(1);
            }

            if (!(packageName.length() > 0)) {
                System.err.println("Bag name must have positive length.");
                System.exit(1);
            }

            if (bagMetadataFile != null && (!bagMetadataFile.exists() || !bagMetadataFile.isFile())) {
                System.err.println("Supplied bag metadata file " + bagMetadataFile.getCanonicalPath() + " does not exist or is not a file.");
                System.exit(1);
            }

			/* Run the package generation application proper */
			application.run();

		} catch (CmdLineException e) {
			/*
			 * This is an error in command line args, just print out usage data
			 * and description of the error.
			 */
			System.err.println(e.getMessage());
			parser.printUsage(System.err);
			System.err.println();
			System.exit(1);
		} catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

   	private void run() throws Exception {
    System.err.println("MOOOOOOOOOOOOOOOOOOOOOOOO");

    }


}
