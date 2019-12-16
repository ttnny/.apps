package main

import (
	"github.com/gorilla/mux"
	"html/template"
	"log"
	"net/http"
	"os"
)

var router = mux.NewRouter()
var tmplPartials = "go_app/templates/_partials.gohtml"

// Register the mux router to serve requests in App Engine
func init() {
	http.Handle("/", router)
}

func main() {
	router.HandleFunc("/", indexHandler).Methods("GET")

	port := os.Getenv("PORT")
	if port == "" {
		port = "8080"
		log.Printf("Defaulting to port %s", port)
	}

	// Serve static files
	router.PathPrefix("/static/").Handler(http.StripPrefix("/static/", http.FileServer(http.Dir("go_app/assets"))))

	log.Printf("Listening on port %s", port)
	if err := http.ListenAndServe(":"+port, nil); err != nil {
		log.Fatal(err)
	}
}

// indexHandler for apps homepage
func indexHandler(w http.ResponseWriter, r *http.Request) {
	tmpl := template.Must(template.ParseFiles(tmplPartials, "go_app/templates/index.gohtml"))
	if err := tmpl.ExecuteTemplate(w, "index.gohtml", nil); err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
	}
}
