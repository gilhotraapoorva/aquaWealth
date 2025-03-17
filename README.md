## Policy Structure:

Policy ID: Unique identifier for each policy (Primary Key)

User ID: Foreign Key linking the policy to a user

Government ID: Unique identifier 

Coverage Type: Specifies the type of insurance 

Coverage Amount: Total amount covered by the policy (must be greater than zero)

Premium Amount: Automatically calculated as Coverage Amount ÷ 30

Start Date: Defaults to the current date when not provided

End Date: Automatically set to Start Date + 1 year
