package com.ben.smith.reader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by bensmith on 5/19/18.
 */
public class Local_Reads {

    // Given a cik folder with 13f's to read, get the path of all of them
    static List<String> extract_files_from_folder(String folder_path) {
        File aDirectory = new File(folder_path);
        String[] filesInDir = aDirectory.list();
        List<String> files = new ArrayList<String>(Arrays.asList(filesInDir));

        for(int i = 0; i < files.size(); i++) {
//            System.out.println(folder_path + "/" + files.get(i));

            files.set(i, folder_path + "/" + files.get(i));
        }

        return files;
    }

    // Read a file into a string list
    static List<String> read_file(String file_name) {
        List<String> text_lines = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file_name));
            String line = br.readLine();
            while (line != null) {
                text_lines.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text_lines;
    }

    // Given a cik folder with 13f's to read, get the path of all of them
    static List<String> determine_broken_files(String folder_path) {
        folder_path = Global_Constants.start_path_for_local_files + folder_path;
        File aDirectory = new File(folder_path);
        String[] filesInDir = aDirectory.list();
        List<String> files = new ArrayList<String>(Arrays.asList(filesInDir));

        System.out.println(folder_path);
        List<String> file_lines;
        for(int i = 0; i < files.size(); i++) {
            file_lines = read_file(folder_path + "/" + files.get(i));

            System.out.println(file_lines);
            System.exit(0);

            // Returns "#" if unreadable.
            boolean is_readable = File_Processor_Old.are_assets_readable(file_lines);
            System.out.println(is_readable);
            if(is_readable == false) {
                System.out.println("Cannot understand file: " + files.get(i));
            }
        }


        return files;
    }

}
