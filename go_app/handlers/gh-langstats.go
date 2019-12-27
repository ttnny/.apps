package handlers

import (
	"encoding/json"
	"html/template"
	"io/ioutil"
	"net/http"
)

type LangChart struct {
	Langs    []string
	Stats    []int
	Username string
	Errors	[]string
}

func GHLangStatsHandler(w http.ResponseWriter, r *http.Request) {
	tmpl := template.Must(template.ParseFiles(tmplPartials,
		"go_app/templates/gh-langstats/langstats.gohtml",
		"go_app/templates/gh-langstats/langchart.gohtml"))

	var langChart LangChart

	if r.Method == http.MethodGet {
		if err := tmpl.ExecuteTemplate(w, "langstats.gohtml", nil); err != nil {
			langChart.Errors = append(langChart.Errors, err.Error())
		}
	}

	if r.Method == http.MethodPost {
		username := r.FormValue("username")

		if username != "" {
			endpoint := "https://61lfz00oi9.execute-api.us-west-2.amazonaws.com/prod/langstats/798798l" + username

			// Call the API endpoint
			response, err := http.Get(endpoint)
			if err != nil {
				http.Error(w, err.Error(), http.StatusBadGateway)
			}

			// Address nil response from the API endpoint
			if response != nil {
				jsonData, err := ioutil.ReadAll(response.Body)
				if err != nil {
					http.Error(w, err.Error(), http.StatusBadGateway)
				}

				langStats := make(map[string]int)
				err = json.Unmarshal(jsonData, &langStats)

				langs := []string{}
				stats := []int{}

				for key, value := range langStats {
					langs = append(langs, key)
					stats = append(stats, value)
				}

				// Prepare dataset for the chart
				langChart.Langs = langs
				langChart.Stats = stats
				langChart.Username = username // for sticky input form

				if err := tmpl.ExecuteTemplate(w, "langstats.gohtml", langChart); err != nil {
					http.Error(w, err.Error(), http.StatusInternalServerError)
				}
			}
		} else {
			if err := tmpl.ExecuteTemplate(w, "langstats.gohtml", nil); err != nil {
				http.Error(w, err.Error(), http.StatusNoContent)
			}
		}
	}
}
