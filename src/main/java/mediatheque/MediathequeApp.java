package mediatheque;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mediatheque.model.Adherent;
import mediatheque.model.DVD;
import mediatheque.model.Document;
import mediatheque.model.Emprunt;
import mediatheque.model.Enseignant;
import mediatheque.model.Etudiant;
import mediatheque.model.JeuDeSociete;
import mediatheque.model.Livre;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignB;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignH;
import org.kordamp.ikonli.materialdesign2.MaterialDesignL;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.materialdesign2.MaterialDesignS;
import org.kordamp.ikonli.materialdesign2.MaterialDesignV;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MediathequeApp extends Application {
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final List<Document> documents = new ArrayList<>();
    private final List<Adherent> adherents = new ArrayList<>();
    private final List<Emprunt> emprunts = new ArrayList<>();
    private final BorderPane root = new BorderPane();
    private final Label title = new Label();
    private final Label subtitle = new Label();
    private TableView<Emprunt> loans;
    private ComboBox<Adherent> memberBox;
    private ComboBox<Document> documentBox;

    @Override
    public void start(Stage stage) {
        seedData();
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        root.getStyleClass().add("app-root");
        root.setLeft(sidebar());
        root.setTop(topbar());
        dashboard();
        Scene scene = new Scene(root, 1240, 780);
        scene.getStylesheets().add(getClass().getResource("/mediatheque.css").toExternalForm());
        stage.setTitle("Médiathèque | Administration");
        stage.setMinWidth(1000);
        stage.setMinHeight(650);
        stage.setScene(scene);
        stage.show();
    }

    private void seedData() {
        Adherent student = new Etudiant("Rakoto", "Fandresena", "E001", LocalDate.of(2025, 9, 1));
        Adherent teacher = new Enseignant("Martin", "Claire", "P014", LocalDate.of(2024, 1, 15));
        adherents.addAll(List.of(student, teacher));
        documents.addAll(List.of(new Livre("Clean Code", "L001", "Robert C. Martin"), new Livre("Le Petit Prince", "L002", "Antoine de Saint-Exupéry"), new DVD("Le Fabuleux Destin d'Amélie Poulain", "D001", 122), new DVD("Interstellar", "D002", 169), new JeuDeSociete("Catan", "J001", 4)));
        emprunts.add(new Emprunt(teacher, documents.get(1), LocalDate.of(2026, 8, 28)));
    }

    private VBox sidebar() {
        VBox bar = new VBox(18);
        bar.getStyleClass().add("sidebar");
        bar.setPadding(new Insets(26, 16, 20, 16));
        HBox brand = new HBox(12, icon("mdi2-book-open-variant", 25), new VBox(2, text("MÉDIATHÈQUE", "brand-title"), text("ESPACE ADMINISTRATION", "brand-caption")));
        brand.setAlignment(Pos.CENTER_LEFT);
        bar.getChildren().addAll(brand, new javafx.scene.control.Separator());
        VBox nav = new VBox(6);
        nav.getChildren().addAll(nav("Vue d'ensemble", "mdi2-view-dashboard", this::dashboard), nav("Catalogue", "mdi2-book-open-variant", this::catalog), nav("Adhérents", "mdi2-account-group", this::members), nav("Emprunts actifs", "mdi2-swap-horizontal", this::loanPage));
        bar.getChildren().add(nav);
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        bar.getChildren().addAll(spacer, new VBox(4, text("Médiathèque locale", "sidebar-muted"), text("v1.0.0  •  Connecté", "sidebar-status")));
        return bar;
    }

    private Button nav(String caption, String glyph, Runnable action) {
        Button button = new Button(caption, icon(glyph, 19));
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.getStyleClass().add("nav-button");
        button.setOnAction(e -> action.run());
        return button;
    }

    private HBox topbar() {
        HBox bar = new HBox(18);
        bar.getStyleClass().add("topbar");
        bar.setAlignment(Pos.CENTER_LEFT);
        bar.setPadding(new Insets(24, 34, 20, 34));
        title.getStyleClass().add("page-title");
        subtitle.getStyleClass().add("page-subtitle");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        bar.getChildren().addAll(new VBox(4, title, subtitle), spacer, text(LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE d MMMM yyyy")), "top-date"), iconButton("mdi2-bell", "Notifications"));
        return bar;
    }

    private void dashboard() {
        heading("Bonjour, gestionnaire", "Pilotez votre médiathèque en un coup d'œil.");
        GridPane stats = new GridPane();
        stats.setHgap(16);
        stats.getColumnConstraints().addAll(grow(), grow(), grow());
        stats.add(kpi("DOCUMENTS AU CATALOGUE", String.valueOf(documents.size()), "Collection disponible", "mdi2-book-multiple", "teal"), 0, 0);
        stats.add(kpi("EMPRUNTS EN COURS", String.valueOf(emprunts.size()), "À surveiller aujourd'hui", "mdi2-clock", "orange"), 1, 0);
        stats.add(kpi("ADHÉRENTS INSCRITS", String.valueOf(adherents.size()), "Profils actifs", "mdi2-account-multiple", "blue"), 2, 0);
        HBox lower = new HBox(18, recentLoans(), quickActions());
        HBox.setHgrow(lower.getChildren().get(0), Priority.ALWAYS);
        VBox content = new VBox(22, stats, lower);
        content.getStyleClass().add("page-content");
        root.setCenter(content);
    }

    private ColumnConstraints grow() { ColumnConstraints c = new ColumnConstraints(); c.setPercentWidth(33.33); c.setHgrow(Priority.ALWAYS); return c; }
    private VBox kpi(String caption, String value, String note, String glyph, String color) {
        VBox card = new VBox(9, new HBox(10, icon(glyph, 20), text(caption, "kpi-label")), text(value, "kpi-value"), text(note, "kpi-note"));
        card.getStyleClass().addAll("kpi-card", color);
        return card;
    }
    private VBox recentLoans() { VBox box = surface("Emprunts récents", "mdi2-history"); TableView<Emprunt> table = loanTable(); box.getChildren().add(table); VBox.setVgrow(table, Priority.ALWAYS); return box; }
    private VBox quickActions() { VBox box = surface("Actions rapides", "mdi2-lightning-bolt"); box.setPrefWidth(245); box.getChildren().addAll(action("Nouvel emprunt", "mdi2-plus", true, this::loanPage), action("Explorer le catalogue", "mdi2-arrow-right", false, this::catalog)); return box; }
    private VBox surface(String caption, String glyph) { VBox box = new VBox(14); box.getStyleClass().add("surface"); box.getChildren().add(new HBox(9, icon(glyph, 18), text(caption, "surface-title"))); return box; }

    private void catalog() {
        heading("Catalogue", "Consultez et recherchez vos documents.");
        VBox box = surface("Collection des documents", "mdi2-book-open-variant");
        TextField search = new TextField(); search.setPromptText("Rechercher un titre, une référence..."); search.setPrefWidth(320);
        TableView<Document> table = documentTable();
        search.textProperty().addListener((obs, old, value) -> table.setItems(FXCollections.observableArrayList(documents.stream().filter(d -> (d.getTitre() + d.getReference()).toLowerCase().contains(value.toLowerCase())).toList())));
        HBox searchRow = new HBox(search); searchRow.setAlignment(Pos.CENTER_RIGHT);
        box.getChildren().addAll(searchRow, table); VBox.setVgrow(table, Priority.ALWAYS); root.setCenter(box);
    }

    private void members() {
        heading("Adhérents", "Suivez les profils et leurs quotas d'emprunt.");
        VBox box = surface("Membres inscrits", "mdi2-account-group");
        TableView<Adherent> table = new TableView<>(FXCollections.observableArrayList(adherents));
        table.getColumns().addAll(col("NOM COMPLET", Adherent::getNomComplet), col("N° ADHÉRENT", Adherent::getNumeroAdherent), col("PROFIL", a -> a instanceof Enseignant ? "Enseignant" : "Étudiant"), col("INSCRIPTION", a -> a.getDateInscription().format(DATE)), col("QUOTA", a -> (a instanceof Enseignant ? ((Enseignant) a).getNombreMaxDocuments() : ((Etudiant) a).getNombreMaxDocuments()) + " documents"));
        box.getChildren().add(table); VBox.setVgrow(table, Priority.ALWAYS); root.setCenter(box);
    }

    private void loanPage() {
        heading("Emprunts actifs", "Enregistrez un prêt ou clôturez un retour.");
        VBox box = surface("Suivi des prêts", "mdi2-swap-horizontal");
        loans = loanTable();
        memberBox = new ComboBox<>(FXCollections.observableArrayList(adherents)); memberBox.setPromptText("Choisir un adhérent");
        documentBox = new ComboBox<>(FXCollections.observableArrayList(documents.stream().filter(d -> !isBorrowed(d)).toList())); documentBox.setPromptText("Choisir un document");
        HBox form = new HBox(10, memberBox, documentBox, action("Enregistrer l'emprunt", "mdi2-plus", true, this::createLoan), action("Enregistrer le retour", "mdi2-check", false, this::returnLoan)); form.getStyleClass().add("loan-form");
        box.getChildren().addAll(loans, form); VBox.setVgrow(loans, Priority.ALWAYS); root.setCenter(box);
    }

    private TableView<Emprunt> loanTable() { TableView<Emprunt> table = new TableView<>(FXCollections.observableArrayList(emprunts)); table.getColumns().addAll(col("DOCUMENT", e -> e.getDocument().getTitre()), col("ADHÉRENT", e -> e.getAdherent().getNomComplet()), col("EMPRUNTÉ LE", e -> e.getDateEmprunt().format(DATE)), col("RETOUR PRÉVU", e -> e.getDateEmprunt().plusDays(duration(e.getDocument())).format(DATE))); return style(table); }
    private TableView<Document> documentTable() { TableView<Document> table = new TableView<>(FXCollections.observableArrayList(documents)); table.getColumns().addAll(col("TITRE", Document::getTitre), col("TYPE", this::typeOf), col("RÉFÉRENCE", Document::getReference), col("INFORMATIONS", this::detailsOf), col("ÉTAT", d -> isBorrowed(d) ? "Emprunté" : "Disponible")); return style(table); }
    private <T> TableColumn<T, String> col(String title, Function<T, String> getter) { TableColumn<T, String> column = new TableColumn<>(title); column.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(getter.apply(cell.getValue()))); return column; }
    private <T> TableView<T> style(TableView<T> table) { table.getStyleClass().add("data-table"); table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN); return table; }
    private Button action(String caption, String glyph, boolean primary, Runnable handler) { Button button = new Button(caption, icon(glyph, 17)); button.getStyleClass().add(primary ? "primary-action" : "secondary-action"); button.setOnAction(e -> handler.run()); return button; }
    private Button iconButton(String glyph, String tip) { Button button = new Button(null, icon(glyph, 20)); button.setTooltip(new javafx.scene.control.Tooltip(tip)); button.getStyleClass().add("icon-button"); return button; }
    private void heading(String main, String sub) { title.setText(main); subtitle.setText(sub); }
    private Label text(String value, String style) { Label label = new Label(value); label.getStyleClass().add(style); return label; }
    private FontIcon icon(String glyph, int size) {
        FontIcon icon = new FontIcon(iconValue(glyph));
        icon.setIconSize(size);
        return icon;
    }

    private Ikon iconValue(String glyph) {
        return switch (glyph) {
            case "mdi2-book-open-variant" -> MaterialDesignB.BOOK_OPEN_VARIANT;
            case "mdi2-view-dashboard" -> MaterialDesignV.VIEW_DASHBOARD;
            case "mdi2-account-group" -> MaterialDesignA.ACCOUNT_GROUP;
            case "mdi2-swap-horizontal" -> MaterialDesignS.SWAP_HORIZONTAL;
            case "mdi2-bell" -> MaterialDesignB.BELL;
            case "mdi2-book-multiple" -> MaterialDesignB.BOOK_MULTIPLE;
            case "mdi2-clock" -> MaterialDesignC.CLOCK;
            case "mdi2-account-multiple" -> MaterialDesignA.ACCOUNT_MULTIPLE;
            case "mdi2-history" -> MaterialDesignH.HISTORY;
            case "mdi2-lightning-bolt" -> MaterialDesignL.LIGHTNING_BOLT;
            case "mdi2-plus" -> MaterialDesignP.PLUS;
            case "mdi2-arrow-right" -> MaterialDesignA.ARROW_RIGHT;
            case "mdi2-check" -> MaterialDesignC.CHECK;
            default -> MaterialDesignB.BOOK;
        };
    }
    private int duration(Document d) { return d instanceof DVD ? ((DVD) d).getDureeMaxPretJours() : d instanceof JeuDeSociete ? ((JeuDeSociete) d).getDureeMaxPretJours() : 14; }
    private boolean isBorrowed(Document d) { return emprunts.stream().anyMatch(e -> e.getDocument() == d); }
    private String typeOf(Document d) { return d instanceof Livre ? "Livre" : d instanceof DVD ? "DVD" : "Jeu"; }
    private String detailsOf(Document d) { return d instanceof Livre ? ((Livre) d).getAuteur() : d instanceof DVD ? ((DVD) d).getDureeMinutes() + " min" : ((JeuDeSociete) d).getNombreJoueursMax() + " joueurs"; }
    private void createLoan() { if (memberBox.getValue() != null && documentBox.getValue() != null) { emprunts.add(new Emprunt(memberBox.getValue(), documentBox.getValue(), LocalDate.now())); loanPage(); } }
    private void returnLoan() { Emprunt loan = loans.getSelectionModel().getSelectedItem(); if (loan != null) { loan.traiterRetourEmprunt(LocalDate.now()); emprunts.remove(loan); loanPage(); } }
}
