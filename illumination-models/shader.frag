precision highp float;

varying vec3 v_normal;

uniform vec3 uLightDir; // Direção da Luz (no fragmento atual)
uniform vec3 uEyeDir; // Direção da câmera (no fragmento atual)

// Intensidade da luz
uniform vec3 uIa; // Intensidade da luz ambiente 
uniform vec3 uId; // Intensidade da luz difusa
uniform vec3 uIs; // Intensidade da luz especular

// Coeficientes do material
uniform vec3 uKa;        // Material cor ambiente
uniform vec3 uKd;        // Material cor difusão
uniform vec3 uKs;        // Material cor especular
uniform vec3 uShininess; // Exponente para o brilho especular. (e.g., 64.0)

void main() {
    // Normalizar os inputs
    vec3 N = normalize(v_normal);
    vec3 L = normalize(uLightDir);
    vec3 V = normalize(uEyeDir);

    // Iluminação ambiente: ka * Ia
    vec3 ambiente = uKa * uIa;

    // Iluminação Difusa: kd * Id * 



  // ambient lighting (global illuminance)
  vec3 ambient = vec3(0.5, 0.5, 0.5); // color - grey

  // diffuse (lambertian) lighting
  // lightColor, lightSource, normal, diffuseStrength
  vec3 normal = normalize(v_normal.xyz);
  vec3 lightColor = vec3(1.0, 1.0, 1.0); // color - white
  vec3 lightSource = vec3(1.0, 1.0, 1.0); // coord - (1, 0, 0)
  float diffuseStrength = max(0.0, dot(lightSource, normal));
  vec3 diffuse = diffuseStrength * lightColor;

  // specular light
  // lightColor, lightSource, normal, specularStrength, viewSource
  vec3 cameraSource = vec3(1.0, 0.0, 0.0);
  vec3 viewSource = normalize(cameraSource);
  vec3 reflectSource = normalize(reflect(-lightSource, normal));
  float specularStrength = max(0.0, dot(viewSource, reflectSource));
  specularStrength = pow(specularStrength, 256.0);
  vec3 specular = specularStrength * lightColor;

  // lighting = ambient + diffuse + specular
  vec3 lighting = vec3(0.0, 0.0, 0.0); // color - black
  // lighting = ambient;
  // lighting = ambient * 0.0 + diffuse;
  // lighting = ambient * 0.0 + diffuse * 0.0 + specular;
  lighting = ambient * 0.9 + diffuse * 0.5 + specular * 0.5;

  // color = modelColor * lighting
  vec3 modelColor = vec3(0.75, 0.75, 0.75);
  vec3 color = modelColor * lighting;

  gl_FragColor = vec4(color, 1.0);
}
