class _1628051():

    ## TODO: Please note that document id should be present till the getPredictions method
    def __tranformation1(self,data):
        import pandas as pd
        import numpy as np
        data=pd.read_csv('Mental_health_data.csv')
        data=data.iloc[:1,:]
       
        def mapping(data):
            Gender = {}
            Gender['Male'] = 0 
            Gender['Female'] = 1 
            Gender['Others'] = 2 

            data.Gender = data.Gender.map(Gender)

            family_history = {}
            family_history['Yes'] = 1
            family_history['No'] = 0
            family_history["Don't know"] = 2

            data.family_history = data.family_history.map(family_history)
            data.treatment = data.treatment.map(family_history)
            data.remote_work = data.remote_work.map(family_history)
            data.benefits = data.benefits.map(family_history)
            data.wellness_program = data.wellness_program.map(family_history)
            data.seek_help = data.seek_help.map(family_history)
            data.anonymity = data.anonymity.map(family_history)
            data.mental_vs_physical = data.mental_vs_physical.map(family_history)

            work_interfere = {}
            work_interfere['Sometimes'] = 0
            work_interfere['Never'] = 1
            work_interfere['Rarely'] = 2 
            work_interfere['Often'] = 3

            data.work_interfere = data.work_interfere.map(work_interfere)

            mental_health_consequence = {}
            mental_health_consequence['Yes'] = 1 
            mental_health_consequence['No'] = 0 
            mental_health_consequence['Maybe'] = 2 

            data.mental_health_consequence = data.mental_health_consequence.map(mental_health_consequence)
            data.phys_health_consequence = data.phys_health_consequence.map(mental_health_consequence)
            data.mental_health_interview = data.mental_health_interview.map(mental_health_consequence)
            data.phys_health_interview = data.phys_health_interview.map(mental_health_consequence)


            coworkers = {}
            coworkers['Yes'] = 1 
            coworkers['No'] = 0 
            coworkers['Some of them'] = 2 

            data.coworkers = data.coworkers.map(coworkers)
            data.supervisor = data.supervisor.map(coworkers)

            return data

        data = mapping(data)
        
        data=data.drop(['work_interfere', 'family_history', 'remote_work','phys_health_interview',
             'coworkers', 'supervisor'],axis=1)
        return data

    def getPredictions(self,data,model):
        data = self.__tranformation1(data)
        # feature list, column names
        features = ['Age', 'Gender', 'treatment', 'benefits', 'wellness_program',
       'seek_help', 'anonymity', 'mental_health_consequence',
       'phys_health_consequence', 'mental_health_interview',
       'mental_vs_physical']#columns name of thoes which has been used to train the model
        predictions = model.predict(data[features].values)
        data['predictions'] = predictions
        pred = data.loc[:,['Emp_ID','predictions']].to_dict(orient="records")
        return pred
