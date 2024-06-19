package org.serverapp.modules;


import java.io.*;


public class FileManager {
    private static File csvCollection = null;
    private static final File TMP_PATH = new File("tmp.csv");

    public static File getCsvCollectionPath() {
        return csvCollection;
    }

    public static File getTmpPath() {
        return TMP_PATH;
    }

    static {
        if (!TMP_PATH.exists()) {
            try {
                new FileWriter(TMP_PATH, false).close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static boolean check(File file) {
        try {
            if (!file.exists()) throw new FileNotFoundException();
        } catch (FileNotFoundException ex) {
            System.out.println("Файла по указанному пути не существует.");
            return false;
        }
        try {
            if (!file.canRead() || !file.canWrite()) throw new SecurityException();
        } catch (SecurityException ex) {
            System.out.println("Файл защищён от чтения и/или записи. Для работы программы нужны оба разрешения.");
            return false;
        }
        return true;
    }

//    public static boolean loadDataToCollection(File file){
//        if(check(file)){
//            csvCollection = file;
//
//            String tmpAns = "";
//            try{
//                if (TMP_PATH.length() > 0) {
//                    System.out.print("У вас есть не сохранённые данные. ");
//                    Scanner tmpScan = new Scanner(System.in);
//                    while(!tmpAns.equals("y") && !tmpAns.equals("n")) {
//                        System.out.print("Хотите восстановить ? (y/n): ");
//                        tmpAns = tmpScan.next();
//                    }
//                }
//
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//
//            if(tmpAns.equals("y")) {
//                file = TMP_PATH;
//            }
//
//            HashMap<File, ArrayList<String>> dict = new HashMap<>();
//            {
//                dict.put(TMP_PATH, new ArrayList<String>(Arrays.asList(
//                        "Идёт восстановление коллекции ",
//                        "Коллекций успешно восстановлены. Восстановлено ",
//                        "Ошибка восстановлении ")));
//                dict.put(csvCollection, new ArrayList<String>(Arrays.asList(
//                        "Идёт загрузка коллекции ",
//                        "Коллекций успешно загружена. Добавлено ",
//                        "Ошибка загрузки ")));
//            }
//            try (BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
//                String nextLine;
//                Person person;
//
//                System.out.println(dict.get(file).get(0));
//
//                ArrayList<String> errors = new ArrayList<>();
//                while ((nextLine = inputStreamReader.readLine()) != null) {
//                    person = csvToPerson(nextLine);
//                    if(person != null) People.people.add(person);
//                    else errors.add(nextLine);
//                }
//
//                System.out.println(dict.get(file).get(1) + People.people.size() + " элементов.");
//
//                if(errors.size() > 0) {
//                    System.out.println(dict.get(file).get(2) + errors.size() + " коллекций:");
//                    for (String e: errors) {
//                        System.out.println("- " + e);
//                    }
//                }
//            }
//            catch (Exception e) {
//                System.out.println(e);
//            }
//            if(People.people.size() > 0) People.setWasChange();
//            return true;
//        }
//        return false;
//    }

//    public static Person csvToPerson(String str){
//        ArrayList<String> ans = new ArrayList<>();
//        StringBuilder tmp = new StringBuilder();
//        boolean brackets = false;
//        char current_symbol;
//        for(int i = 0; i < str.length(); i++) {
//            current_symbol = str.charAt(i);
//            if(current_symbol == '\"') {
//                if(!brackets || (i + 1 != str.length() && str.charAt(i + 1) != '\"')) {
//                    brackets = !brackets;
//                    continue;
//                }
//
//                else if(i + 1 != str.length() && str.charAt(i + 1) == '\"') {
//                    i++;
//                }
//                else {
//                    return null;
//                }
//            }
//            else if(current_symbol == ',' && !brackets) {
//                ans.add(tmp.toString());
//                tmp = new StringBuilder();
//                continue;
//            }
//            tmp.append(current_symbol);
//
//        }
//        ans.add(tmp.toString());
//        if(ans.size() < 9) return null;
//
//        try {
//            Integer id = Integer.valueOf(ans.get(0));
//            String name = ans.get(1);
//            Coordinates coordinates = new Coordinates(ans.get(2));
//            ZonedDateTime creationDate = ZonedDateTime.parse(ans.get(3));
//            long height = Long.parseLong(ans.get(4));
//            LocalDateTime birthday = (ans.get(5).equals("null")) ? null : LocalDateTime.parse(ans.get(5), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            Color hairColor = (ans.get(6).equals("null")) ? null : Color.valueOf(ans.get(6));
//            Country nationality = (ans.get(7).equals("null")) ? null : Country.valueOf(ans.get(7));
//            Location location = (ans.get(8).equals("null")) ? null : new Location(ans.get(8));
//
//            return new Person(id, name, coordinates, creationDate, height, birthday, hairColor, nationality, location);
//        }
//        catch (Exception e) {
//            return null;
//        }
//    }

}
