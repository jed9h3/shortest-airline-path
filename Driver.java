import java.io.File;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Driver extends Application {
    static int vcount;
    static int adjcount;
    static Label pathL = new Label();
    static Label distanceL = new Label();
    static Pane layout = new Pane();

    @Override
    public void start(Stage primaryStage) throws Exception {
        File file = new File("Cities.txt");
        Scanner input = new Scanner(file);
        ImageView mapimg = new ImageView(new Image("file:imgs/map.png"));
        VBox sidevbox = new VBox(100);
        HBox bottomhbox = new HBox(80);
        HBox vbox1 = new HBox(10);
        HBox vbox2 = new HBox(10);
        VBox hb1 = new VBox(20);
        VBox hb2 = new VBox(20);
        Label l1 = new Label("Source");
        Label l2 = new Label("Target");
        ComboBox<String> sourceCb = new ComboBox<>();
        ComboBox<String> targetCb = new ComboBox<>();
        Button runb = new Button("Run");
        distanceL.setStyle("-fx-border-color: #EBE6E5; -fx-border-width: 3");
        pathL.setStyle("-fx-border-color: #EBE6E5; -fx-border-width: 3");
        Label l3 = new Label("Path");
        Label l4 = new Label("Distance");
        vbox1.getChildren().addAll(l3, pathL);
        vbox2.getChildren().addAll(l4, distanceL);
        hb1.getChildren().addAll(l1, sourceCb);
        hb2.getChildren().addAll(l2, targetCb);
        sidevbox.getChildren().addAll(hb1, hb2, runb);
        bottomhbox.getChildren().addAll(vbox2, vbox1);
        l1.setTextFill(Color.web("#FF0022"));
        l2.setTextFill(Color.web("#1A00FF"));
        l3.setTextFill(Color.web("#EBE6E5"));
        l4.setTextFill(Color.web("#EBE6E5"));
        l1.setFont(new Font("Arial", 25));
        l2.setFont(new Font("Arial", 25));
        l3.setFont(new Font("Arial", 25));
        l4.setFont(new Font("Arial", 25));
        runb.setTextFill(Color.BLACK);
        distanceL.setFont(new Font("Arial", 18));
        pathL.setFont(new Font("Arial", 18));
        pathL.setPrefSize(800, 50);
        pathL.setTextFill(Color.WHITESMOKE);
        distanceL.setTextFill(Color.WHITESMOKE);
        distanceL.setMinSize(270, 50);
        runb.setMinSize(100, 35);
        runb.setMaxSize(100, 35);
        runb.setFont(new Font("Arial", 20));
        BorderPane bp1 = new BorderPane();
        bp1.setStyle("-fx-background-color: #222222");
        Scene scene = new Scene(bp1);
        StackPane sp = new StackPane();
        sp.getChildren().addAll(mapimg, layout);
        sp.setAlignment(Pos.TOP_LEFT);
        sidevbox.setPadding(new Insets(100, 20, 20, 20));
        hb1.setAlignment(Pos.CENTER);
        hb2.setAlignment(Pos.CENTER);
        bottomhbox.setPadding(new Insets(0, 0, 30, 20));
        vbox1.setAlignment(Pos.CENTER);
        vbox2.setAlignment(Pos.CENTER);
        sourceCb.setScaleX(1.2);
        sourceCb.setScaleY(1.2);
        targetCb.setScaleX(1.2);
        targetCb.setScaleY(1.2);
        bp1.setCenter(sp);
        bp1.setRight(sidevbox);
        bp1.setBottom(bottomhbox);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
        // ========================================================
        String[] temp = new String[3];
        double[] te = new double[2];
        temp = input.nextLine().split(" ");
        vcount = Integer.parseInt(temp[0]);
        adjcount = Integer.parseInt(temp[1]);
        Node[] table = new Node[vcount];
        String[] typeop = new String[vcount + 1];
        typeop[0] = " ";
        for (int i = 0; i < vcount; i++) {
            temp = input.nextLine().split(" ");
            ImageView pickie = new ImageView(new Image("file:imgs/mcircle.png"));
            te = translateCoordinates(Double.parseDouble(temp[1]),
                    Double.parseDouble(temp[2]));
            layout.getChildren().add(pickie);
            pickie.setX(te[0] - 8);
            pickie.setY(te[1] - 8);
            City tcity = new City(temp[0], Double.parseDouble(temp[1]), Double.parseDouble(temp[2]));
            tcity.xie = te[0];
            tcity.yie = te[1];
            typeop[i + 1] = temp[0];
            table[i] = (new Node(tcity));
            pickie.setOnMouseClicked(ei -> {
                if ((sourceCb.getSelectionModel().isEmpty() || sourceCb.getValue().compareTo(" ") == 0)
                        || (targetCb.getSelectionModel().isEmpty() || targetCb.getValue().compareTo(" ") == 0)
                        || (pickie.getImage().getUrl().compareTo("file:imgs/mcircle.png") != 0)) {
                    if (sourceCb.getSelectionModel().isEmpty() || sourceCb.getValue().compareTo(" ") == 0) {
                        for (int u = 0; u < table.length; u++) {
                            if ((table[u].vertix.xie) == (pickie.getX() + 8)
                                    && (table[u].vertix.yie) == (pickie.getY() + 8)) {
                                sourceCb.setValue(table[u].vertix.name);
                                break;
                            }
                        }
                    } else if (pickie.getImage().getUrl().compareTo("file:imgs/redpick.png") == 0) {
                        sourceCb.setValue(" ");
                    } else {
                        if (targetCb.getSelectionModel().isEmpty() || targetCb.getValue().compareTo(" ") == 0) {
                            for (int u = 0; u < table.length; u++) {
                                if ((table[u].vertix.xie) == (pickie.getX() + 8)
                                        && (table[u].vertix.yie) == (pickie.getY() + 8)) {
                                    targetCb.setValue(table[u].vertix.name);
                                    break;
                                }
                            }
                        } else {
                            targetCb.setValue(" ");
                        }
                    }
                }
            });
        }
        ObservableList<String> items = FXCollections.observableArrayList(typeop);
        sourceCb.getItems().addAll(items);
        targetCb.getItems().addAll(items);
        while (input.hasNext()) {
            temp = input.nextLine().split(" ");
            int[] gett = find(table, temp[0], temp[1]);
            table[gett[0]].adj.addlast(gett[1]);
        }
        input.close();
        runb.setDisable(true);
        sourceCb.setOnAction(oq -> {
            for (int f = 0; f < layout.getChildren().size(); f++) {
                if (layout.getChildren().get(f) instanceof ImageView) {
                    if (((ImageView) layout.getChildren().get(f))
                            .getImage().getUrl().compareTo("file:reddpick.png") == 0) {
                        ((ImageView) layout.getChildren().get(f)).setImage(new Image("file:imgs/mcircle.png"));
                    }
                }
            }
            while (layout.getChildren().get(layout.getChildren().size() - 1) instanceof Arrow) {
                layout.getChildren().remove(layout.getChildren().size() - 1);
            }
            if (sourceCb.getSelectionModel().isEmpty() ||
                    targetCb.getSelectionModel().isEmpty()
                    || sourceCb.getValue().compareTo(" ") == 0 ||
                    targetCb.getValue().compareTo(" ") == 0) {
                runb.setDisable(true);
            } else {
                runb.setDisable(false);
            }
            if (sourceCb.getSelectionModel().isEmpty() ||
                    sourceCb.getValue().compareTo(" ") == 0) {
                for (int k = 0; k < layout.getChildren().size(); k++) {
                    if (layout.getChildren().get(k) instanceof ImageView && ((ImageView) layout.getChildren().get(k))
                            .getImage().getUrl().compareTo("file:imgs/redpick.png") == 0) {
                        ((ImageView) layout.getChildren().get(k))
                                .setX(((ImageView) layout.getChildren().get(k)).getX() + 15 - 8);
                        ((ImageView) layout.getChildren().get(k))
                                .setY(((ImageView) layout.getChildren().get(k)).getY() + 30 - 8);
                        ((ImageView) layout.getChildren().get(k)).setImage(new Image("file:imgs/mcircle.png"));
                        break;
                    }
                }
            } else {
                for (int k = 0; k < table.length; k++) {
                    if (sourceCb.getValue().compareTo(table[k].vertix.name) == 0) {
                        for (int l = 0; l < layout.getChildren().size(); l++) {
                            if (layout.getChildren().get(l) instanceof ImageView) {
                                if (((ImageView) layout.getChildren().get(l)).getImage().getUrl()
                                        .compareTo("file:imgs/redpick.png") == 0) {
                                    ((ImageView) layout.getChildren().get(l))
                                            .setX(((ImageView) layout.getChildren().get(l)).getX() + 15 - 8);
                                    ((ImageView) layout.getChildren().get(l))
                                            .setY(((ImageView) layout.getChildren().get(l)).getY() + 30 - 8);
                                    ((ImageView) layout.getChildren().get(l))
                                            .setImage(new Image("file:imgs/mcircle.png"));
                                }
                            }
                        }
                        for (int l = 0; l < layout.getChildren().size(); l++) {
                            if (layout.getChildren().get(l) instanceof ImageView) {
                                if (((ImageView) layout.getChildren().get(l))
                                        .getImage().getUrl().compareTo("file:imgs/mcircle.png") == 0) {
                                    if (((ImageView) layout.getChildren().get(l)).getX() + 8 == table[k].vertix.xie
                                            && ((ImageView) layout.getChildren().get(l)).getY()
                                                    + 8 == table[k].vertix.yie) {
                                        ((ImageView) layout.getChildren().get(l))
                                                .setX(((ImageView) layout.getChildren().get(l)).getX() + 8 - 15);
                                        ((ImageView) layout.getChildren().get(l))
                                                .setY(((ImageView) layout.getChildren().get(l)).getY() + 8 - 30);
                                        ((ImageView) layout.getChildren().get(l))
                                                .setImage(new Image("file:imgs/redpick.png"));
                                        break;
                                    }
                                } else {
                                    if (((ImageView) layout.getChildren().get(l)).getX() + 15 == table[k].vertix.xie
                                            && ((ImageView) layout.getChildren().get(l)).getY()
                                                    + 30 == table[k].vertix.yie) {

                                        ((ImageView) layout.getChildren().get(l))
                                                .setImage(new Image("file:imgs/redpick.png"));
                                        targetCb.setValue(" ");
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }
        });
        targetCb.setOnAction(we -> {
            for (int f = 0; f < layout.getChildren().size(); f++) {
                if (layout.getChildren().get(f) instanceof ImageView) {
                    if (((ImageView) layout.getChildren().get(f))
                            .getImage().getUrl().compareTo("file:reddpick.png") == 0) {
                        ((ImageView) layout.getChildren().get(f)).setImage(new Image("file:imgs/mcircle.png"));
                    }
                }
            }
            while (layout.getChildren().get(layout.getChildren().size() - 1) instanceof Arrow) {
                layout.getChildren().remove(layout.getChildren().size() - 1);
            }
            if (sourceCb.getSelectionModel().isEmpty() || targetCb.getSelectionModel().isEmpty()
                    || sourceCb.getValue().compareTo(" ") == 0 || targetCb.getValue().compareTo(" ") == 0) {
                runb.setDisable(true);
            } else {
                runb.setDisable(false);
            }
            if (targetCb.getSelectionModel().isEmpty() ||
                    targetCb.getValue().compareTo(" ") == 0) {
                for (int k = 0; k < layout.getChildren().size(); k++) {
                    if (layout.getChildren().get(k) instanceof ImageView && ((ImageView) layout.getChildren().get(k))
                            .getImage().getUrl().compareTo("file:imgs/bluepick.png") == 0) {
                        ((ImageView) layout.getChildren().get(k))
                                .setX(((ImageView) layout.getChildren().get(k)).getX() + 15 - 8);
                        ((ImageView) layout.getChildren().get(k))
                                .setY(((ImageView) layout.getChildren().get(k)).getY() + 30 - 8);
                        ((ImageView) layout.getChildren().get(k)).setImage(new Image("file:imgs/mcircle.png"));
                        break;
                    }
                }
            } else {
                for (int k = 0; k < table.length; k++) {
                    if (targetCb.getValue().compareTo(table[k].vertix.name) == 0) {
                        for (int l = 0; l < layout.getChildren().size(); l++) {
                            if (layout.getChildren().get(l) instanceof ImageView) {
                                if (((ImageView) layout.getChildren().get(l)).getImage().getUrl()
                                        .compareTo("file:imgs/bluepick.png") == 0) {
                                    ((ImageView) layout.getChildren().get(l))
                                            .setX(((ImageView) layout.getChildren().get(l)).getX() + 15 - 8);
                                    ((ImageView) layout.getChildren().get(l))
                                            .setY(((ImageView) layout.getChildren().get(l)).getY() + 30 - 8);
                                    ((ImageView) layout.getChildren().get(l))
                                            .setImage(new Image("file:imgs/mcircle.png"));
                                }
                            }
                        }
                        for (int l = 0; l < layout.getChildren().size(); l++) {
                            if (layout.getChildren().get(l) instanceof ImageView) {
                                if (((ImageView) layout.getChildren().get(l))
                                        .getImage().getUrl().compareTo("file:imgs/mcircle.png") == 0) {
                                    if (((ImageView) layout.getChildren().get(l)).getX() + 8 == table[k].vertix.xie
                                            && ((ImageView) layout.getChildren().get(l)).getY()
                                                    + 8 == table[k].vertix.yie) {
                                        ((ImageView) layout.getChildren().get(l))
                                                .setX(((ImageView) layout.getChildren().get(l)).getX() + 8 - 15);
                                        ((ImageView) layout.getChildren().get(l))
                                                .setY(((ImageView) layout.getChildren().get(l)).getY() + 8 - 30);
                                        ((ImageView) layout.getChildren().get(l))
                                                .setImage(new Image("file:imgs/bluepick.png"));
                                        break;
                                    }
                                } else {
                                    if (((ImageView) layout.getChildren().get(l)).getX() + 15 == table[k].vertix.xie
                                            && ((ImageView) layout.getChildren().get(l)).getY()
                                                    + 30 == table[k].vertix.yie) {

                                        ((ImageView) layout.getChildren().get(l))
                                                .setImage(new Image("file:imgs/bluepick.png"));
                                        sourceCb.setValue(" ");
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }
        });
        runb.setOnAction(ie -> {
            dijkstra(table, find(table, sourceCb.getValue(), targetCb.getValue()));
            for (int f = 0; f < layout.getChildren().size(); f++) {
                if (layout.getChildren().get(f) instanceof ImageView) {
                    if (((ImageView) layout.getChildren().get(f))
                            .getImage().getUrl().compareTo("file:imgs/mcircle.png") == 0) {
                        ((ImageView) layout.getChildren().get(f)).setImage(new Image("file:reddpick.png"));
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void dijkstra(Node[] table, int[] fromto) {
        Heap pqueue = new Heap(vcount);
        Node v, w;
        StringBuilder sb = new StringBuilder();
        sb.append(fromto[0] + ",");
        table[fromto[0]].distance = 0;
        pqueue.insert(table[fromto[0]]);
        while (true) {
            v = pqueue.remove();
            if (v == null || table[fromto[1]].known) {
                break;
            }
            v.known = true;
            Link p = v.adj.getfirst();
            while (p != null) {
                w = table[p.data];
                if (!w.known) {
                    double dista = calculateDistance(v.vertix, w.vertix);
                    if (v.distance + dista < w.distance) {
                        w.distance = dista + v.distance;
                        w.pNode = v;
                        pqueue.insert(w);
                        sb.append(p.data + ",");
                    }
                }
                p = p.next;
            }
        }
        pathL.setText(" " + printPath(table[fromto[1]], new StringBuilder()));
        if (pathL.getText().contains("->")) {
            distanceL.setText(" " + table[fromto[1]].distance + "KM");
        } else {
            distanceL.setText(" 0.0 KM");
            pathL.setText(pathL.getText() + table[fromto[0]].vertix.name);
        }
        String[] eli = sb.toString().split(",");
        for (int q = 0; q < eli.length; q++) {
            table[Integer.parseInt(eli[q])].pNode = null;
            table[Integer.parseInt(eli[q])].known = false;
            table[Integer.parseInt(eli[q])].distance = Double.MAX_VALUE;
        }
    }

    public static String printPath(Node k, StringBuilder sb) {
        if (k.pNode != null) {
            printPath(k.pNode, sb);
            sb.append(k.vertix.name + "->");
            Arrow arrow = new Arrow();
            double[] temp = translateCoordinates(k.pNode.vertix.lat, k.pNode.vertix.lon);
            arrow.setStartX(temp[0]);
            arrow.setStartY(temp[1]);
            temp = translateCoordinates(k.vertix.lat, k.vertix.lon);
            arrow.setEndX(temp[0]);
            arrow.setEndY(temp[1]);
            layout.getChildren().add(arrow);
        } else {
            sb.append(k.vertix.name + "->");
        }
        String lonly = sb.substring(0, sb.length() - 2);
        if (lonly.contains("->")) {
            return sb.toString().substring(0, sb.length() - 2);
        } else {
            return lonly + " Cant be reached from ";
        }
    }

    public static double[] translateCoordinates(double lat, double lon) {
        double[] result = new double[2];
        double radius = 1195.0 / (2.0 * Math.PI);
        double latRad = Math.toRadians(lat);
        double lonRad = Math.toRadians(lon + 180.0);
        result[0] = (lonRad * radius);
        double yFromEquator = radius * Math.log(Math.tan(Math.PI / 4.0 + latRad / 2.0));
        result[1] = (565.0 / 2.0 - yFromEquator);
        result[0] -= 15;
        result[1] -= 30;
        result[1] += 82;
        result[0] -= 15;
        for (int h = 1; h < 10; h++) {
            if ((result[0] + 15) < 580) {
                if ((result[0] + 15) > 580 - (h * 68)) {
                    result[0] -= (h * 3);
                    break;
                }
            } else {
                if ((result[0] + 15) < 580 + (h * 68)) {
                    result[0] += (h * 1.5);
                    break;
                }
            }
        }
        for (int g = 1; g < 5; g++) {
            if ((result[1] + 30) > 360) {
                if ((result[1] + 30) < 360 + (g * 90)) {
                    result[1] += 2;
                    break;
                }
            } else {
                if ((result[1] + 30) > 360 - (g * 90)) {
                    result[1] += ((g * 5) - ((result[1]) / 45));
                    break;
                }
            }
        }
        result[0] += 15;
        result[1] += 30;
        return result;
    }

    public static int[] find(Node[] table, String source, String target) {
        int[] result = new int[2];
        int flag = 0;
        for (int o = 0; o < table.length; o++) {
            if (source.compareTo(table[o].vertix.name) == 0) {
                result[0] = o;
                flag++;
            }
            if (target.compareTo(table[o].vertix.name) == 0) {
                result[1] = o;
                flag++;
            }
            if (flag == 2) {
                break;
            }
        }
        return result;
    }

    public static double calculateDistanceb(double lat1, double lat2, double lon1, double lon2) {
        double side1 = (lat1 - lat2) * 111.1;
        double side2 = (lon1 - lon2) * 111.1 * Math.cos(Math.toRadians(lat1));
        side1 = Math.pow(side1, 2);
        side2 = Math.pow(side2, 2);
        return Math.sqrt(side1 + side2);
    }

    public static double calculateDistance(City l1, City l2) {
        double lon1 = Math.toRadians(l1.lon);
        double lon2 = Math.toRadians(l2.lon);
        double lat1 = Math.toRadians(l1.lat);
        double lat2 = Math.toRadians(l2.lat);
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double r = 6371;
        return (c * r);
    }
}