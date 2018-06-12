package gameplay;
import java.io.*;
import java.util.*;
@SuppressWarnings({"unused"})
public class Files 
{
    private String file;
    PrintWriter out;
    public Files(String fileName,boolean rw)
    {
        file = fileName;
        //open and create the new file
        if(rw)//if we are choosing the write a NEW file
        {
            try
            {
                out = new PrintWriter(fileName);
            }
            catch(FileNotFoundException t){}
        }

    }

    public void writeFile(String [] data)
    {
        //open and create the new file
        try
        {
            out = new PrintWriter(file);
        }
        catch(FileNotFoundException t){}
        for(int i = 0;i<data.length;i++)
            out.println(data[i]);//add the date to the file
        out.close();//close the file
    }


    public String[] readFile()
    {
        String[] t ;
        try
        {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader); 
            String line ="";
            ArrayList<String> tempString = new ArrayList<String>();
            int temp = 0;
            try
            {
                while((line = br.readLine())!= null)//reads the file until the last line
                {
                    tempString.add(line);//store the information in an ArrayList
                    temp++;//add to the position var
                }
                //Collections.sort(tempString);
                //To sort in reverse order

                //Collections.sort(tempString, Collections.reverseOrder()); 
                t = new String[tempString.size()];
                for(int i = 0; i<tempString.size();i++)
                {
                    t[i] = tempString.get(i);
                }
                reader.close();//close the reader file
                return t;//returns the String Array in order
            }
            catch(IOException q){}
        }
        catch(FileNotFoundException ee){}
        return null;
    }

}