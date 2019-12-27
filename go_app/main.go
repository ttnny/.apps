package main

import (
	"github.com/gorilla/mux"
	"github.com/ttnny/apps/go_app/handlers"
	"log"
	"net/http"
	"os"
)

var router = mux.NewRouter()

// Register the mux router to serve requests in App Engine
func init() {
	http.Handle("/", router)
}

func main() {
	// Routing to handlers
	router.HandleFunc("/", handlers.IndexHandler)
	router.HandleFunc("/gh-langstats", handlers.GHLangStatsHandler)
	router.HandleFunc("/gh-ctbngraph", handlers.GHCtbnGraphHandler)

	// Serve static files
	router.PathPrefix("/static/").Handler(http.StripPrefix("/static/", http.FileServer(http.Dir("go_app/assets"))))

	port := os.Getenv("PORT")
	if port == "" {
		port = "8080"
		log.Printf("Defaulting to port %s", port)
	}

	log.Printf("Listening on port %s", port)
	if err := http.ListenAndServe(":"+port, nil); err != nil {
		log.Fatal(err)
	}
}