##
# Song Library Design
#
# @file
# @version 0.1

JFLAGS = --module-path $(PATH_TO_FX) --add-modules javafx.controls,javafx.fxml,javafx.graphics -d ../bin
APP = songlib/app
IO = songlib/io
VIEW = songlib/view
BINAPP = ../bin/$(APP)
BINIO = ../bin/$(IO)
BINVIEW = ../bin/$(VIEW)

all: $(BINAPP)/SongLib.class $(BINIO)/Song.class $(BINIO)/SongLibIO.class $(BINIO)/test.class $(BINVIEW)/SongLibController.class $(BINVIEW)/SongLibAddController.class $(BINVIEW)/SongLibEditController.class $(BINVIEW)/SongLibDeleteController.class $(BINVIEW)/SongLib.fxml $(BINVIEW)/add.fxml $(BINVIEW)/edit.fxml $(BINVIEW)/delete.fxml

$(BINAPP)/SongLib.class: $(APP)/SongLib.java
	javac $(JFLAGS) $(APP)/SongLib.java

$(BINIO)/Song.class: $(IO)/Song.java
	javac $(JFLAGS) $(IO)/Song.java

$(BINIO)/SongLibIO.class: $(IO)/SongLibIO.java
	javac $(JFLAGS) $(IO)/SongLibIO.java

$(BINIO)/test.class: $(IO)/test.java
	javac $(JFLAGS) $(IO)/test.java

$(BINVIEW)/SongLibController.class: $(VIEW)/SongLibController.java
	javac $(JFLAGS) $(VIEW)/SongLibController.java

$(BINVIEW)/SongLibAddController.class: $(VIEW)/SongLibAddController.java
	javac $(JFLAGS) $(VIEW)/SongLibAddController.java

$(BINVIEW)/SongLibEditController.class: $(VIEW)/SongLibEditController.java
	javac $(JFLAGS) $(VIEW)/SongLibEditController.java

$(BINVIEW)/SongLibDeleteController.class: $(VIEW)/SongLibDeleteController.java
	javac $(JFLAGS) $(VIEW)/SongLibDeleteController.java

$(BINVIEW)/SongLib.fxml: $(VIEW)/SongLib.fxml
	cp $(VIEW)/SongLib.fxml $(BINVIEW)

$(BINVIEW)/add.fxml: $(VIEW)/add.fxml
	cp $(VIEW)/add.fxml $(BINVIEW)

$(BINVIEW)/edit.fxml: $(VIEW)/edit.fxml
	cp $(VIEW)/edit.fxml $(BINVIEW)

$(BINVIEW)/delete.fxml: $(VIEW)/delete.fxml
	cp $(VIEW)/delete.fxml $(BINVIEW)

clean:
	rm -r ../bin/*

# end
