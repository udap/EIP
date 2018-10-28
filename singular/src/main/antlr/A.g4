grammar A;
stat: 'return' e ';' # Return
    | 'breaka'    ';' # Break
    ;
e   : e '*' e        # Mult
    | e '+' e        # Add
    | INT            # Int
    ;
