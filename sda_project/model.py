class BMIModel:
    @staticmethod
    def calculate_bmi(height_feet, height_inches, weight):
        try:
            height_inches_total = height_feet * 12 + height_inches
            bmi = 703 * weight / (height_inches_total ** 2)
            return bmi, 0  # Return BMI and code 0 for success
        except ZeroDivisionError:
            return None, 1  # Return None and code 1 for division by zero error
        except Exception as e:
            return None, 2  # Return None and code 2 for other exceptions