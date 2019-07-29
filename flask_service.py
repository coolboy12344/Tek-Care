### TODO: Make a Flask app which acts as a prediction engine, it will receives data from a client and send back the
### TODO: predictions to the client in the form of JSON
 
from flask import Flask, request
import urllib.parse
import json
 
from sklearn.externals import joblib
import importlib
import pandas as pd
from flask_cors import CORS
 
app = Flask(__name__)
CORS(app)
def __str2Class(module_name, type):
    # load the module, will raise ImportError if module cannot be loaded
    class_name = '_' + str(module_name)
    module_name = type + '.' + module_name
 
    m = importlib.import_module(module_name)
    # get the class, will raise AttributeError if class cannot be found
    c = getattr(m, class_name)
    return c
 
 
 
@app.route('/predict', methods=['GET'])
def predict():
    if request.method == 'GET':
        #data = str(request.data).split("data=")[-1]
        #data = urllib.parse.unquote(data)[:-1]
 
        ## Format of JSON = { "data":"rows wise data","id":"roll number }
        data = request.args.get("data")
 
        # JSON data converted to dictionary
        data = json.loads(data)
 
        id = data["id"]
 
        dataFrame = pd.DataFrame(data['data'])
 
        # get required files by parsing ID
        pickleFile = str(id) + ".pkl"
        transformationFile = str(id)
 
        # loading the model and the transformation class
        model = joblib.load(r"D:\WORK - MATERIAL\MATERIAL\PYTHON WORK\High Radius\Teksystem\Files\TekSys.joblib")
        myClass = __str2Class(transformationFile,"Files")
        myClassInstance = myClass()
 
        predictions = myClassInstance.getPredictions(dataFrame,model)
        print(predictions)
        return str(predictions)
    
if __name__ == '__main__':
    app.run(threaded=True)