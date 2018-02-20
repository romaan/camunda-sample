#!flask/bin/python
from flask import Flask, jsonify, request, abort

app = Flask(__name__)

tasks = [
    {
        'orderID': 1000,
        'startTime': u'2013-02-12T23:00:00.000Z',
        'endTime': u'2015-02-12T23:00:00.000Z',
        'flowRate': 12
    },
    {
        'orderID': 2,
        'startTime': u'2013-03-12T23:00:00.000Z',
        'endTime': u'2015-03-12T23:00:00.000Z',
        'flowRate': 21
    }
]

@app.route('/todo/api/v1.0/tasks', methods=['GET'])
def get_tasks():
    return jsonify({'tasks': tasks})

@app.route('/todo/api/v1.0/tasks', methods=['POST'])
def create_task():
    if not request.json or not 'orderID' in request.json:
        abort(400)
    task = {
        'orderID': request.json.get('orderID'),
        'outletID': request.json.get('outletID'),
        'startTime': request.json.get('startTime'),
        'endTime': request.json.get('endTime'),
        'flowRate': request.json.get('flowRate')
    }
    tasks.append(task)
    return jsonify({'task': task}), 201


@app.route('/todo/api/v1.0/orders', methods=['POST'])
def create_orders():
    if not request.json:
        abort(400)
    for task in request.json:
        task = {
            'orderID': task.get('orderID'),
            'outletID': task.get('outletID'),
            'startTime': task.get('startTime'),
            'endTime': task.get('endTime'),
            'flowRate': task.get('flowRate')
        }
        tasks.append(task)
    return jsonify({'task': task}), 201

if __name__ == '__main__':
    app.run(debug=True)
