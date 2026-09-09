from flask import jsonify
from model import  BMIModel

class BMIController:
    def _init_(self):
        self.model = BMIModel()

    def compute_bmi(self, height_feet, height_inches, weight):
        try:
            # Convert input values to integers
            height_feet = int(height_feet)
            height_inches = int(height_inches)
            weight = int(weight)
            
            # Calculate BMI using the model
            bmi = self.model.calculate_bmi(height_feet, height_inches, weight)
            
            # Determine BMI category based on the calculated BMI
            bmi_category = self._determine_bmi_category(bmi)
            
            # Return BMI and BMI category to the view
            return jsonify({'bmi': bmi, 'bmi_category': bmi_category}), 200
        except ValueError:
            # Handle invalid input data (e.g., non-numeric values)
            return jsonify({'error': 'Invalid input: Please enter numeric values for height and weight.'}), 400
        except Exception as e:
            # Handle other exceptions
            return jsonify({'error': str(e)}), 500

    def _determine_bmi_category(self, bmi):
        if bmi is None:
            return None  # Return None if BMI is not calculated
        elif bmi < 18.5:
            return 'Underweight'
        elif 18.5 <= bmi < 25:
            return 'Normal weight'
        elif 25 <= bmi < 30:
            return 'Overweight'
        else:
            return 'Obese'

