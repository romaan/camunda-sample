# Rest Service

Runs a dummy rest service. Inorder to install:

virtualenv .env
source .env/bin/activate
pip install Flask

Inorder to run:

python app.py

Inorder to run using curl:

curl -i http://localhost:5000/todo/api/v1.0/tasks
curl -i -H "Content-Type: application/json" -X POST -d '{"title":"Read a book"}' http://localhost:5000/todo/api/v1.0/tasks
