const messages = {
  auth: {
    signIn: "Sign in",
    signUp: "Sign up",
    signOut: "Sign out",
    email: "e-mail*",
    psw: "password*",
    confirmPsw: " confirm password*",
    accQuest: "Don't have an account?",
    forgot: "Forgot Password? ",
    haveAcc: "Already have an account?",
    remember: "Remember Me",
    firstN: "first name*",
    lastN: "last name*",
  },
  title: {
    createAcc: "Create an account",
    createPlant: "Create a plant",
    updatePlant: "Update plant",
    common: "Common name",
    latin: "Latin name",
    hyd: "Hydration",
    sun: "Sunligt",
    hydration: "Hydration level",
    sunlight: "Sunlight level",
    img: "Image",
    desc: "Description",
    action: "Action",
    selectHyd: "Select an hydratation level",
    selectSun: "Select a sunlight level",
    noResult: "No results found!",
    myPlants: "My plants",
    home: "Home",
    dashboard: "My account",
  },
  helper: {
    common: "Plant's common name",
    latin: "Plant's latin name",
    hydration: "Plant's hydration level",
    sunlight: "Plant's sunlight level",
    img: "Plant's image",
    desc: "Plant's description",
  },
  error: {
    common: "Maximum of 100 chars",
    latin: "Maximum of 200 chars",
    required: "The value is required",
    img: "Image size must be less than 500ko.",
    desc: "Maximum of 1000 chars",
    try: "An error occured. Please try again.",
    credentials: "Invalid email or password",
    customValid: {
      emailExists: "This email is already associated with an account.",
      confirmedPsw: "Password and confirmed password does not match. ",
      uniqueCommonName: "This common name exists already",
      uniqueLatinName: "This latin name exists already",
    },
  },
  success: {
    add: "Plant added with success.",
    create: "Plant created with success.",
    update: "Plant updated with success.",
    delete: "Plant deleted with success.",
  },
  btn: {
    save: "Save",
  },
};

export default messages;
