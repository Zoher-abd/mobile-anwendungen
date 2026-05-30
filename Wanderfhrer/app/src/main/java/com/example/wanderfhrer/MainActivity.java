public void neueRoute(View v ){
    FileInputStream fis = null;
    try {
        fis = openFileInput(FILE_NAMEBank);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String text;
        FileWriter fileWriter = new FileWriter(getApplicationContext());
        String begin = null;
        String ende = null;
        while((text = br.readLine())!= null){
            if(begin == null){
                begin = text;
            }
            if(ende == null && br.readLine() == null){
                ende = text;
            }
        }
        String[] beginArr = begin.split(",");
        String[] endeArr = ende.split(",");
        String []beginZeit = beginArr[0].split(" ");
        String [] beginTime = beginZeit[1].split(":");
        String beginTimefinish= beginTime[0]+beginTime[1]+beginTime[2];
        String []endeZeit = endeArr[0].split(" ");
        String [] endeTime = endeZeit[1].split(":");
        String endeTimefinish= endeTime[0]+endeTime[1]+endeTime[2];
        String duration = endeTimefinish + beginTimefinish;
        String timeStamp = "Route " + beginZeit[0];
        String gpxName = "gpx" + beginZeit[0];
        route = new Route(timeStamp,beginArr[1] + beginArr[2],endeArr[1] +
                endeArr[2],gpxName,duration);
        AppDatabase db = Room.databaseBuilder(RouteEntryActivity.this, AppDatabase.class,
                "routes").build();
        RouteDao routeDao = db.routeDao();
        routeDao.insert(route);
        System.out.println("Geklappt");
        fileWriter.stopRecording();
        File file = fileWriter.createNewFile();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Path path = file.toPath();
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }finally {
        if(fis != null ){
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
}
______________
@Override
public void onLocationChanged(Location location) {
    String strLocation = String.format("%f, %f", location.getLatitude(), location.getLongitude());
    textView.setText(strLocation);
    double longitudeView = location.getLongitude();
    double latitudeView = location.getLatitude();
    mLongitude = (TextView) textView.findViewById(R.id.txtLongitude);
    mlatitude = (TextView) textView.findViewById(R.id.txtLatitude);
    mLongitude.setText(String.format("Long: %.6f °",longitudeView));
    mlatitude.setText(String.format("Lati: %.6f °", latitudeView));
//csv
    FileOutputStream fos = null;
    try {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date());
        String longitude = (String) mLongitude.getText();
        String latitude = (String) mlatitude.getText();
        savetext += time + ", " + longitude + ", " + latitude +"\n";
        fos = context.openFileOutput(FILE_NAMEBank, MODE_PRIVATE);
        fos.write(savetext.getBytes());
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }finally{
        if(fos != null){
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}