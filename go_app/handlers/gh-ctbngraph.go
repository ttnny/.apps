package handlers

import (
	"html/template"
	"net/http"
)

type CtbnGraph struct {
	Ctbn     template.HTML
	Username string
}

func GHCtbnGraphHandler(w http.ResponseWriter, r *http.Request) {

}