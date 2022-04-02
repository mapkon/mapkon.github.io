.DEFAULT_GOAL := dev

dev:
	clj -M -m cljs.main -d out -c mapkon.blog -r
test:
	echo "Implement teh tests"
build:
	clj -M -m cljs.main -O advanced -c mapkon.blog
