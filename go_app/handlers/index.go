package handlers

import (
	"html/template"
	"net/http"
)

var tmplPartials = "go_app/templates/partials.gohtml"

// IndexHandler for apps homepage
func IndexHandler(w http.ResponseWriter, r *http.Request) {
	tmpl := template.Must(template.ParseFiles(tmplPartials, "go_app/templates/index.gohtml"))

	err := tmpl.ExecuteTemplate(w, "index.gohtml", nil)
	if err != nil {
		tmpl.ExecuteTemplate(w, "partials.gohtml", err)
	}
}
