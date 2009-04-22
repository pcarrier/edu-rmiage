#!/bin/bash

for i in `seq 5`; do
	pdflatex cahier_charges_afnor.tex
done;

rm *.log
rm *.aux
