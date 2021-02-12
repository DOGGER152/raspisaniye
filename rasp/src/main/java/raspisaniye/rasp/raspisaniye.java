package raspisaniye.rasp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import javax.swing.*;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class raspisaniye
{
static class Parser
{
int day, month, year, dayk, monthk, yeark;

void set1(int d1, int m1, int y1)
{
day = d1;
month = m1;
year = y1;
}

void set2(int d2, int m2, int y2)
{
dayk = d2;
monthk = m2;
yeark = y2;
}

Document getPage() throws IOException
{
String url = "http://services.niu.ranepa.ru/wp-content/plugins/rasp/rasp_json_data.php?user=Ик-022&&dstart=" + day + "." + month + "." + year + "&&dfinish=" + dayk + "." + monthk + "." + yeark;
Connection connection = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true); 
Connection.Response resp = connection.execute(); 
Document document = null;
if (resp.statusCode() ==200) {
    document = connection.get();
    ; 
}
return document;
}
}
static class vk { 
public void vk() throws URISyntaxException { 
    URI uri = new URI("https://vk.com/alex.flegontov");
    Desktop desktop1 = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
    if (desktop1 != null && desktop1.isSupported(Desktop.Action.BROWSE)) {
        try {
            desktop1.browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}} 

public static class email { 
	public void email() throws URISyntaxException { 
		String message = "mailto:flegontov008@outlook.com?subject=Расписание%20Windows";
	   URI email = URI.create(message);
	   Desktop desktop2 = Desktop.getDesktop(); 
	   if (desktop2 != null && desktop2.isSupported(Desktop.Action.MAIL)) { 
		   try {
			desktop2.mail(email);
		} catch (IOException e) {
			e.printStackTrace();
		}
	   }
	}
}

public static void main(String[] args) throws IOException, ParseException {
Calendar calendar = new GregorianCalendar();
Calendar calendar2 = new GregorianCalendar();

int[] dat1 = new int[3];
dat1[0] = calendar.get(calendar.DAY_OF_MONTH);
dat1[1] = calendar.get(calendar.MONTH) + 1;
dat1[2] = calendar.get(calendar.YEAR);

int dk = calendar2.get(calendar2.DAY_OF_MONTH) + 7;
int mk = calendar2.get(calendar2.MONTH) + 1;
int yk = calendar2.get(calendar2.YEAR);
int m = calendar.getActualMaximum(calendar.DAY_OF_MONTH);

if (dk > m) {
calendar2.add(calendar2.DAY_OF_MONTH, +7);
calendar2.add(calendar2.MONTH, +2);
dk = calendar2.get(calendar2.DAY_OF_MONTH);
mk = calendar2.get(calendar2.MONTH);
yk = calendar2.get(calendar2.YEAR);
}

Parser obj = new Parser();
obj.set1(dat1[0], dat1[1], dat1[2]);
obj.set2(dk, mk, yk);

StringBuilder content = new StringBuilder();
content.append("<html><head></head><body><table border='1' width='100%'>");

try {
String page = obj.getPage().body().html();

JSONParser p = new JSONParser();
JSONObject jo = (JSONObject) p.parse(page);
JSONArray ri = (JSONArray) ((JSONObject) jo.get("GetRaspGroupResult")).get("RaspItem");


final String[] header = {""};

ri.forEach(item -> {
String date = ((JSONObject) item).get("date").toString();
System.out.println("date: " + date);
System.out.println("header: " + header[0]);

if (!date.equals(header[0])) {
header[0] = date;
content.append("<tr><td colspan='5' align='center'>" + date + "</td></tr>");
content.append("<tr><td>Начало</td><td>Окончание</td><td>Предмет</td><td>Аудитория</td><td>Группа</td></tr>");
}

content.append(
"<tr><td>" + ((JSONObject) item).get("timestart").toString() +
"</td><td>" + ((JSONObject) item).get("timefinish").toString() +
"</td><td>" + ((JSONObject) item).get("name").toString() +
"</td><td>" + ((JSONObject) item).get("aydit").toString() +
"</td><td>" + ((JSONObject) item).get("namegroup").toString() + "</td></tr>");
//System.out.println(item.toString());
});
}
catch (Exception e)
{
content.append("<tr><td colspan='5' align='center'>Нет данных</td></tr>");
}

content.append("</table></body></html>");

JFrame frame = new JFrame("Расписание");
JEditorPane txt = new JEditorPane(); 
txt.setEditable( false );
txt.setContentType( "text/html;Content-Type=UTF-8" );
txt.setText(content.toString()); 



JScrollPane sp = new JScrollPane( txt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
sp.getViewport().add( txt );
sp.revalidate();
frame.setIconImage(Toolkit.getDefaultToolkit().getImage("\\250.jpg"));
frame.add(sp);
frame.setSize(800, 600); 
frame.setLocationRelativeTo(txt);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);
}
}